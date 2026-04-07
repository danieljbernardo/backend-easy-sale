package com.easysale.backend.repository;

import com.easysale.backend.domain.venda.Venda;
import com.easysale.backend.domain.venda.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Pagamento findByVenda(Venda venda);
}
