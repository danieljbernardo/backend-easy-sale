package com.easysale.backend.domain.venda.itemVenda;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CriarItemVendaDTO(
        @NotNull
        Long vendaId,

        @NotNull
        Long produtoCodigo,

        @NotNull
        Long quantidade,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "1000000.0")
        @Digits(integer=7, fraction = 2)
        BigDecimal precoUnitario,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "1000000.0")
        @Digits(integer=7, fraction = 2)
        BigDecimal subtotal
) {
}
