package com.easy_sale.backend.domain.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record BuscarVendaDTO(

        @CPF
        String cpf,

        @PastOrPresent
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data

) {
}
