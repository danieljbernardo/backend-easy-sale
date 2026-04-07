package com.easysale.backend.service;

import com.easysale.backend.domain.produto.EditarProdutoDTO;
import com.easysale.backend.domain.produto.Produto;
import com.easysale.backend.domain.produto.ProdutoDTO;
import com.easysale.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ResponseEntity cadastrandoProduto(ProdutoDTO produtoDTO){
        if(produtoRepository.findByCodigo(produtoDTO.codigo())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto já cadastrado no sistema");
        }
        Produto produto=new Produto(produtoDTO.nome(), produtoDTO.codigo(), produtoDTO.preco(),produtoDTO.descricao());
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deletandoProduto(Long codigo){
        if(produtoRepository.findByCodigo(codigo)==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O Produto já não existe no sistema");
        }
        Produto produto=produtoRepository.findByCodigo(codigo);
        produtoRepository.delete(produto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity buscandoProduto(Long codigo){
        if(produtoRepository.findByCodigo(codigo)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado no sistema");
        }
        Produto produto=produtoRepository.findByCodigo(codigo);
        return ResponseEntity.ok().body(new ProdutoDTO(produto.getNome(),produto.getPreco(),
                produto.getCodigo(), produto.getDescricao()));
    }

    public ResponseEntity editandoProduto(EditarProdutoDTO editarProdutoDTO) {
        Produto produto=this.produtoRepository.findByCodigo(editarProdutoDTO.codigo());
        if(!editarProdutoDTO.nome().isBlank()){
            produto.setNome(editarProdutoDTO.nome());
        }
        if(!editarProdutoDTO.descricao().isBlank()){
            produto.setDescricao(editarProdutoDTO.descricao());
        }
        if(editarProdutoDTO.codigo()!=null){
            produto.setCodigo(editarProdutoDTO.codigo());
        }
        if(editarProdutoDTO.preco()!=null){
            produto.setPreco(editarProdutoDTO.preco());
        }

        this.produtoRepository.save(produto);
        return ResponseEntity.ok().build();

    }
}
