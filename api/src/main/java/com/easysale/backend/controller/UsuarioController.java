package com.easysale.backend.controller;

import com.easysale.backend.domain.venda.BuscarVendaDTO;
import com.easysale.backend.service.PdfService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/easysale/usuario")
public class UsuarioController {

    @Autowired
    PdfService pdfService;

    @PostMapping("/gerar-relatorio")
    public ResponseEntity gerandoRelatorioVenda(@RequestBody @Valid BuscarVendaDTO buscarVendaDTO){
        byte[] relatorio=pdfService.gerarRelatorioVenda(buscarVendaDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",
                "relatorio.pdf");

        return ResponseEntity.ok().headers(headers).body(relatorio);
    }
}
