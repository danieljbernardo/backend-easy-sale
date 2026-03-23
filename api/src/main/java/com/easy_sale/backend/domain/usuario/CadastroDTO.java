package com.easy_sale.backend.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroDTO(

        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @NotBlank
        @Email
        String email,

        @NotNull
        @NotBlank
        @Size(min = 8)
        String senha,

        @NotNull
        @NotBlank
        String role


) {
}
