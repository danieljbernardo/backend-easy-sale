package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.venda.*;
import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.EnviarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.ItemVenda;
import com.easy_sale.backend.domain.venda.pagamento.FormaPagamento;
import com.easy_sale.backend.domain.venda.pagamento.Pagamento;
import com.easy_sale.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    NotaFiscalRepository notaFiscalRepository;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PdfService pdfService;

    public ResponseEntity<byte[]> cadastrandoVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();
        venda.setCliente(this.clienteRepository.findByCpf(vendaDTO.cpf()));

        List<Long> produtos = Arrays.stream(vendaDTO.produtos().split(","))
                .map(Long::parseLong)
                .toList();
        List<Long> quantidades = Arrays.stream(vendaDTO.quantidades().split(","))
                .map(Long::parseLong)
                .toList();

        if (produtos.size() != quantidades.size()) {
            throw new IllegalArgumentException("Número de produtos não corresponde ao número de quantidades");
        }

        BigDecimal valorDaVenda = BigDecimal.ZERO;

        for (int i = 0; i < produtos.size(); i++) {
            Long codigoProduto = produtos.get(i);
            Long quantidade = quantidades.get(i);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);

            Produto produto = this.produtoRepository.findByCodigo(codigoProduto);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(quantidade);
            itemVenda.setPrecoUnitario(produto.getPreco());

            BigDecimal quantidadeBigDecimal = new BigDecimal(quantidade);
            valorDaVenda = valorDaVenda.add(itemVenda.getPrecoUnitario().multiply(quantidadeBigDecimal));
            this.itemVendaRepository.save(itemVenda);
        }

        venda.setValorTotal(valorDaVenda);

        FormaPagamento formaPagamento = FormaPagamento.valueOf(vendaDTO.formaPagamento().toUpperCase());
        Pagamento pagamento = new Pagamento(venda, formaPagamento);
        venda.setPagamento(pagamento);
        NotaFiscal notaFiscal = new NotaFiscal(venda.getValorTotal(), venda);
        venda.setNotaFiscal(notaFiscal);
        this.notaFiscalRepository.save(notaFiscal);
        this.pagamentoRepository.save(pagamento);
        this.vendaRepository.save(venda);

        byte[] pdfNotaFiscal = this.pdfService.gerarNotaFiscal(notaFiscal, venda);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "nota_fiscal_" + notaFiscal.getNumeroNota() + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfNotaFiscal);
    }

    public ResponseEntity deletandoVenda(Long id) {
        if (this.vendaRepository.findByIdVenda(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("Essa venda já não existe no sistema"));
        }
        Venda venda = this.vendaRepository.findByIdVenda(id);
        this.vendaRepository.delete(venda);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity buscandoVenda(BuscarVendaDTO buscarVendaDTO){
        List<Venda> vendas=this.vendaRepository.findByCpfData(buscarVendaDTO.cpf(), buscarVendaDTO.data());
        List<EnviarVendaDTO> resposta=vendas.stream()
                .map(venda->{

                        List<EnviarItemVendaDTO> itensDTO=this.itemVendaRepository.findByVenda(venda).stream()
                                .map(itemVenda -> new EnviarItemVendaDTO( itemVenda.getId(),
                                        itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getNome(), itemVenda.getQuantidade(),
                                        itemVenda.getPrecoUnitario()
                                ))
                                .toList();

                       return new EnviarVendaDTO(venda.getCliente().getCpf(),venda.getNotaFiscal().getNumeroNota(),
                        venda.getPagamento().getFormaPagamento().getFormaPagamentoString(),
                        venda.getValorTotal(), venda.getDataVenda(), itensDTO);
                })
                .toList();
        return ResponseEntity.ok().body(resposta);
    }

    public ResponseEntity editandoVenda(EditarVendaDTO editarVendaDTO){
        if(this.clienteRepository.findByCpf(editarVendaDTO.clienteCpf())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já existente no sistema");
        }
        if(this.notaFiscalRepository.findByNumeroNota(editarVendaDTO.notaFiscalNumero())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Número de nota fiscal já existente no sistema");
        }
        Venda venda=this.vendaRepository.findByIdVenda(editarVendaDTO.notaFiscalNumero());
        if(!editarVendaDTO.clienteCpf().isBlank()){
            venda.setCliente(this.clienteRepository.findByCpf(editarVendaDTO.clienteCpf()));
        }
        if(editarVendaDTO.notaFiscalNumero()!=null){
            venda.setNotaFiscal(this.notaFiscalRepository.findByNumeroNota(editarVendaDTO.notaFiscalNumero()));
        }
        if(!editarVendaDTO.pagamentoForma().isBlank()){
            FormaPagamento formaPagamento=FormaPagamento.valueOf(editarVendaDTO.pagamentoForma().toUpperCase());
            Pagamento pagamento=this.pagamentoRepository.findByVenda(venda);
            pagamento.setFormaPagamento(formaPagamento);
            venda.setPagamento(pagamento);
        }
        if(editarVendaDTO.valorTotal()!=null){
            venda.setValorTotal(editarVendaDTO.valorTotal());
        }
        if(editarVendaDTO.dataVenda()!=null){
            venda.setDataVenda(editarVendaDTO.dataVenda());
        }
        this.vendaRepository.save(venda);
        return ResponseEntity.ok().build();
    }

}
