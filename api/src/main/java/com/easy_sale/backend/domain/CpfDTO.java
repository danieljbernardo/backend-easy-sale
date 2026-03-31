package com.easy_sale.backend.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CpfDTO (

        @NotBlank
        @CPF
        String cpf

){
}
