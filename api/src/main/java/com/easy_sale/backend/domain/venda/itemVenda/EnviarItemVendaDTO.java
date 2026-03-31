package com.easy_sale.backend.domain.venda.itemVenda;

import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.venda.Venda;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EnviarItemVendaDTO(

        @NotNull
        @Min(value = 1)
        Long itemVendaId,

        @NotNull
        @Min(100)
        @Max(999)
        Long produtoCodigo,

        @NotNull
        String produtoNome,

        @NotNull
        @Min(value = 1)
        Long quantidade,

        @NotNull
        BigDecimal precoUnitario

) {
}
