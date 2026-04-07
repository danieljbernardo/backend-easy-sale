package com.easysale.backend.domain.venda;

import com.easysale.backend.domain.venda.itemVenda.EnviarItemVendaDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EnviarVendaDTO(

        String clienteCpf,

        Long notaFiscalNumero,

        @NotBlank
        String pagamentoForma,

        @NotNull
        BigDecimal valorTotal,

        @NotNull
        LocalDate dataVenda,

        @NotNull
        List<EnviarItemVendaDTO> itensVenda

) {
}
