package com.easysale.backend.domain.venda.itemVenda;


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
        BigDecimal precoUnitario,

        @NotNull
        BigDecimal subtotal

) {
}
