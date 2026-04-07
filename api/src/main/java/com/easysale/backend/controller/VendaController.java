package com.easysale.backend.controller;

import com.easysale.backend.domain.produto.CodigoDTO;
import com.easysale.backend.domain.venda.BuscarVendaDTO;
import com.easysale.backend.domain.venda.EditarVendaDTO;
import com.easysale.backend.domain.venda.VendaDTO;
import com.easysale.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/venda")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @PostMapping(value = "/cadastrar-venda", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity cadastrarVenda(@RequestBody @Valid VendaDTO vendaDTO){
        return this.vendaService.cadastrandoVenda(vendaDTO);
    }

    @DeleteMapping("/deletar-venda")
    public ResponseEntity deletarVenda(@RequestBody @Valid CodigoDTO codigoDTO){
        return vendaService.deletandoVenda(codigoDTO.codigo());
    }

    @PostMapping("/buscar-venda")
    public ResponseEntity buscarVenda(@RequestBody @Valid BuscarVendaDTO buscarVendaDTO){
        return vendaService.buscandoVenda(buscarVendaDTO);
    }

    @PatchMapping("/editar-venda")
    public ResponseEntity editarVenda(@RequestBody @Valid EditarVendaDTO editarVendaDTO){
        return vendaService.editandoVenda(editarVendaDTO);
    }

}
