package com.easysale.backend.domain.venda;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record VendaDTO (

        @NotBlank
        @CPF
        String cpf,

        @NotBlank
        String formaPagamento,

        @NotBlank
        String produtos,

        @NotBlank
        String quantidades

){
}
