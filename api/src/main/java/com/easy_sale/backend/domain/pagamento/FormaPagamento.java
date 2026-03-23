package com.easy_sale.backend.domain.pagamento;

public enum FormaPagamento {

    PIX("pix"),
    CARTAO("cartão"),
    BOLETO("boleto"),
    DINHEIRO("dinheiro");

    private String formaPagamento;

    FormaPagamento(String formaPagamento){
        this.formaPagamento=formaPagamento;
    }

    public String getFormaPagamento(){
        return formaPagamento;
    }

}
