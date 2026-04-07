package com.easysale.backend.domain.venda.pagamento;

public enum FormaPagamento {

    PIX("pix"),
    CARTAOCREDITO("cartão crédito"),
    CARTAODEBITO("cartão débito"),
    BOLETO("boleto"),
    DINHEIRO("dinheiro");

    private final String formaPagamentoString;

    FormaPagamento(String formaPagamento){
        this.formaPagamentoString=formaPagamento;
    }

    public String getFormaPagamentoString(){
        return formaPagamentoString;
    }

}
