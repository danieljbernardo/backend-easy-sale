package com.easysale.backend.domain.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EditarVendaDTO (

        String clienteCpf,

        @Min(value = 1)
        @NotNull
        Long vendaId,

        String pagamentoForma,

        @DecimalMin(value = "0.0", inclusive = false)
        @DecimalMax(value = "1000000.0")
        @Digits(integer=7, fraction = 2)
        BigDecimal valorTotal,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataVenda

){
}
