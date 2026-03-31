package com.easy_sale.backend.service;

import com.easy_sale.backend.domain.venda.itemVenda.EditarItemVendaDTO;
import com.easy_sale.backend.domain.venda.itemVenda.ItemVenda;
import com.easy_sale.backend.repository.ItemVendaRepository;
import com.easy_sale.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemVendaService {

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public ResponseEntity editandoItemVenda(EditarItemVendaDTO editarItemVendaDTO){
        ItemVenda itemVenda=this.itemVendaRepository.findByIdItemVenda(editarItemVendaDTO.itemVendaId());
        if(editarItemVendaDTO.produtoCodigo()!=null){
            itemVenda.setProduto(this.produtoRepository.findByCodigo(editarItemVendaDTO.produtoCodigo()));
            itemVenda.setPrecoUnitario(this.produtoRepository.findByCodigo(editarItemVendaDTO.produtoCodigo()).getPreco());
        }
        if(editarItemVendaDTO.quantidade()!=null){
            itemVenda.setQuantidade(editarItemVendaDTO.quantidade());
        }
        itemVendaRepository.save(itemVenda);
        return ResponseEntity.ok().build();
    }
}
