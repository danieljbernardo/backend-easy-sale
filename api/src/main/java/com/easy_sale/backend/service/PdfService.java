package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.venda.itemVenda.ItemVenda;
import com.easy_sale.backend.domain.venda.NotaFiscal;
import com.easy_sale.backend.domain.venda.Venda;
import com.easy_sale.backend.repository.ClienteRepository;
import com.easy_sale.backend.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;


@Service
public class PdfService {

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired

    public byte[] gerarNotaFiscal(NotaFiscal notaFiscal, Venda venda) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            Paragraph cabecalho = new Paragraph("NOTA FISCAL DE VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecalho);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Número: " + notaFiscal.getNumeroNota(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("DADOS DA VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            PdfPTable tableVenda = new PdfPTable(2);
            tableVenda.setWidthPercentage(100);
            tableVenda.addCell("Data:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tableVenda.addCell(notaFiscal.getDataEmissao().format(formatter));
            tableVenda.addCell("Forma Pagamento:");
            tableVenda.addCell(venda.getPagamento().getFormaPagamento().getFormaPagamentoString());
            document.add(tableVenda);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("ITENS DA VENDA",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            PdfPTable tableItens = new PdfPTable(4);
            tableItens.setWidthPercentage(100);
            tableItens.setWidths(new float[]{3, 1, 1, 1});

            tableItens.addCell("Produto");
            tableItens.addCell("Quantidade");
            tableItens.addCell("Valor Unitário");
            tableItens.addCell("Subtotal");

            ArrayList<ItemVenda> itensDaVenda=itemVendaRepository.findByVenda(venda);

            NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

            for (ItemVenda item : itensDaVenda) {
                tableItens.addCell(item.getProduto().getNome());
                tableItens.addCell(item.getQuantidade().toString());
                tableItens.addCell(moeda.format(item.getPrecoUnitario()));
                tableItens.addCell(moeda.format(item.getSubTotal()));
            }

            document.add(tableItens);
            document.add(Chunk.NEWLINE);

            Paragraph total = new Paragraph("VALOR TOTAL: "+moeda.format(notaFiscal.getValorTotal()),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar nota fiscal", e);
        }
    }

    public byte[] gerarRelatorioVenda(Venda venda){
        try(ByteArrayOutputStream baos=new ByteArrayOutputStream()){
            Document document=new Document();
            PdfWriter.getInstance(document,baos);
            document.open();

            Paragraph cabecalho=new Paragraph("Relatório de Vendas",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD,18));
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            document.add(cabecalho);

            document.add(Chunk.NEWLINE);


            PdfPTable tabelaVendas=new PdfPTable(6);



            for()




        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }
}
