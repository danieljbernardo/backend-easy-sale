package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.service.ItemVendaService;
import com.easy_sale.backend.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item-venda")
public class ItemVendaController {

    @Autowired
    ItemVendaService itemVendaService;

    @PostMapping("/editar-item-venda")
    public ResponseEntity editarItemVenda(@RequestBody @Valid EditarItemVendaDTO editarItemVendaDTO){
        return itemVendaService.editandoItemVenda(editarItemVendaDTO);
    }
}
