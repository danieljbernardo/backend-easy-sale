package com.easy_sale.backend.domain.produto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CodigoDTO (

        @NotNull
        @Min(value = 100)
        @Max(value = 999)
        Long codigo
){
}
