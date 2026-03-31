package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.produto.Produto;
import com.easy_sale.backend.domain.venda.itemVenda.ItemVenda;
import com.easy_sale.backend.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    ArrayList<ItemVenda> findByVenda(Venda venda);
    ItemVenda findByIdItemVenda(Long id);
}
