package com.easy_sale.backend.domain.pagamento;

import com.easy_sale.backend.domain.Venda;
import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    private FormaPagamento formaPagamento;

    public Pagamento() {
    }

    public Pagamento(Venda venda, FormaPagamento formaPagamento) {
        this.venda = venda;
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
