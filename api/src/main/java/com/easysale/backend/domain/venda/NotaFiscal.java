package com.easysale.backend.domain.venda;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroNota;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    private BigDecimal subTotal=BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDateTime dataEmissao;

    public NotaFiscal(BigDecimal subTotal, Venda venda) {
        this.subTotal = subTotal;
        this.venda = venda;
    }

    public NotaFiscal() {
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValorTotal() {
        return subTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.subTotal = valorTotal;
    }

    public Long getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Long numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
