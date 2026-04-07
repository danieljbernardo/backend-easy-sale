package com.easysale.backend.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record EditarClienteDTO (

        String nome,

        @CPF
        @NotBlank
        String cpf,

        @Pattern(regexp = "^(\\+55\\s?)?\\(?\\d{2}\\)?\\s?(9?\\d{4})-?\\d{4}$")
        String telefone){
}
