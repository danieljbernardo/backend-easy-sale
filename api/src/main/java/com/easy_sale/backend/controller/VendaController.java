package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.produto.CodigoDTO;
import com.easy_sale.backend.domain.venda.BuscarVendaDTO;
import com.easy_sale.backend.domain.venda.EditarVendaDTO;
import com.easy_sale.backend.domain.venda.VendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venda")
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
