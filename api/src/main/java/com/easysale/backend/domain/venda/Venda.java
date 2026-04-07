package com.easysale.backend.domain.venda;

import com.easysale.backend.domain.cliente.Cliente;
import com.easysale.backend.domain.venda.itemVenda.ItemVenda;
import com.easysale.backend.domain.venda.pagamento.Pagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ItemVenda> itensVendidos;

    @OneToOne(mappedBy = "venda")
    @JsonIgnore
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "venda")
    @JsonIgnore
    private Pagamento pagamento;

    private BigDecimal valorTotal=BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDate dataVenda;

    public Venda() {
    }

    public Venda(Cliente cliente, NotaFiscal notaFiscal, Pagamento pagamento, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.notaFiscal = notaFiscal;
        this.pagamento = pagamento;
        this.valorTotal=valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItensVendidos() {
        return itensVendidos;
    }

    public void setItensVendidos(List<ItemVenda> itensVendidos) {
        this.itensVendidos = itensVendidos;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
