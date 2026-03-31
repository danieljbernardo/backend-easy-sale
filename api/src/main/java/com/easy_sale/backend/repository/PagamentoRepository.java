package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.venda.Venda;
import com.easy_sale.backend.domain.venda.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Pagamento findByVenda(Venda venda);
}
