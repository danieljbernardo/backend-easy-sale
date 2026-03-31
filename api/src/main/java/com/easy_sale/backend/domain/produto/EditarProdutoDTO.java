package com.easy_sale.backend.domain.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record EditarProdutoDTO(

        String nome,

        @Min(100)
        @Max(999)
        @NotNull
        Long codigo,

        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "1000000.0")
        @Digits(integer=7, fraction = 2)
        BigDecimal preco,

        String descricao
) {
}
