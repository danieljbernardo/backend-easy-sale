package com.easysale.backend.service;

import com.easysale.backend.domain.produto.Produto;
import com.easysale.backend.domain.venda.*;
import com.easysale.backend.domain.venda.*;
import com.easysale.backend.domain.venda.itemVenda.EnviarItemVendaDTO;
import com.easysale.backend.domain.venda.itemVenda.ItemVenda;
import com.easysale.backend.domain.venda.pagamento.FormaPagamento;
import com.easysale.backend.domain.venda.pagamento.Pagamento;
import com.easysale.backend.repository.*;
import com.easysale.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        List<ItemVenda> itensParaSalvar = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {

            ItemVenda itemVenda = new ItemVenda();

            Produto produto = this.produtoRepository.findByCodigo(produtos.get(i));
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(quantidades.get(i));
            itemVenda.setPrecoUnitario(produto.getPreco());

            BigDecimal quantidadeBigDecimal = new BigDecimal(quantidades.get(i));
            valorDaVenda = valorDaVenda.add(itemVenda.getPrecoUnitario().multiply(quantidadeBigDecimal));
            itemVenda.setSubtotal(itemVenda.getPrecoUnitario().multiply(quantidadeBigDecimal));
            itensParaSalvar.add(itemVenda);
        }

        FormaPagamento formaPagamento = FormaPagamento.valueOf(vendaDTO.formaPagamento().toUpperCase());
        Pagamento pagamento = new Pagamento(venda, formaPagamento);
        venda.setPagamento(pagamento);
        NotaFiscal notaFiscal = new NotaFiscal(venda.getValorTotal(), venda);
        venda.setNotaFiscal(notaFiscal);

        venda.setValorTotal(valorDaVenda);
        this.notaFiscalRepository.save(notaFiscal);
        this.pagamentoRepository.save(pagamento);
        Venda vendaSalva=this.vendaRepository.save(venda);

        for(ItemVenda item: itensParaSalvar){
            item.setVenda(vendaSalva);
            this.itemVendaRepository.save(item);
        }

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
        List<Venda> vendas=this.vendaRepository.findByCpfAndData(buscarVendaDTO.cpf(), buscarVendaDTO.data());
        List<EnviarVendaDTO> resposta=vendas.stream()
                .map(venda->{

                        List<EnviarItemVendaDTO> itensDTO=this.itemVendaRepository.findByVenda(venda).stream()
                                .map(itemVenda -> new EnviarItemVendaDTO( itemVenda.getId(),
                                        itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getNome(), itemVenda.getQuantidade(),
                                        itemVenda.getPrecoUnitario(), itemVenda.getSubtotal()
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
        Venda venda=this.vendaRepository.findByIdVenda(editarVendaDTO.vendaId());
        if(!editarVendaDTO.clienteCpf().isBlank()){
            venda.setCliente(this.clienteRepository.findByCpf(editarVendaDTO.clienteCpf()));
        }
        if(editarVendaDTO.vendaId()!=null){
            venda.setNotaFiscal(this.notaFiscalRepository.findByNumeroNota(editarVendaDTO.vendaId()));
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
