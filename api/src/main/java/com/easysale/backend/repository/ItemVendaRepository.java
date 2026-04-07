package com.easysale.backend.repository;

import com.easysale.backend.domain.produto.Produto;
import com.easysale.backend.domain.venda.itemVenda.ItemVenda;
import com.easysale.backend.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    ArrayList<ItemVenda> findByVenda(Venda venda);

    ItemVenda findByIdItemVenda(Long id);

    ItemVenda findByVendaAndProduto(Venda venda, Produto produto);
}
