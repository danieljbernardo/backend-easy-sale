package com.easysale.backend.domain.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoDTO (

        @NotBlank
        String nome,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "1000000.0")
        @Digits(integer=7, fraction = 2)
        BigDecimal preco,

        @NotNull
        @Min(100)
        @Max(999)
        Long codigo,

        @NotBlank
        String descricao

){
}
