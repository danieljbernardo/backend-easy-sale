package com.easy_sale.backend.domain.venda;

import com.easy_sale.backend.domain.venda.itemVenda.EnviarItemVendaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EnviarVendaDTO(

        @NotNull
        String clienteCpf,

        @NotNull
        Long notaFiscalNumero,

        @NotBlank
        String pagamentoForma,

        @NotNull
        BigDecimal valorTotal,

        @NotNull
        LocalDate dataVenda,

        @NotNull
        List<EnviarItemVendaDTO> ItensVenda

) {
}
