package com.easysale.backend.controller;

import com.easysale.backend.domain.produto.CodigoDTO;
import com.easysale.backend.domain.produto.EditarProdutoDTO;
import com.easysale.backend.domain.produto.ProdutoDTO;
import com.easysale.backend.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/cadastrar-produto")
    public ResponseEntity cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
        return this.produtoService.cadastrandoProduto(produtoDTO);
    }

    @DeleteMapping("/deletar-produto")
    public ResponseEntity deletarProduto(@RequestBody @Valid CodigoDTO codigoDTO){
        return this.produtoService.deletandoProduto(codigoDTO.codigo());
    }

    @GetMapping("/buscar-produto")
    public ResponseEntity buscarProduto(@RequestBody @Valid CodigoDTO codigoDTO){
        return this.produtoService.buscandoProduto(codigoDTO.codigo());
    }

    @PatchMapping("/editar-produto")
    public ResponseEntity editarProduto(@RequestBody @Valid EditarProdutoDTO editarProdutoDTO){
        return this.produtoService.editandoProduto(editarProdutoDTO);
    }
}
