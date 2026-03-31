package com.easy_sale.backend.domain.venda.itemVenda;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record EditarItemVendaDTO(

        @Min(value = 1)
        @NotNull
        Long itemVendaId,

        @Min(100)
        @Max(999)
        Long produtoCodigo,

        @Min(value = 1)
        Long quantidade
) {
}
