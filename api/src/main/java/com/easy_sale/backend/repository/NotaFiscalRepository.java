package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.venda.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    NotaFiscal findByNumeroNota(Long numeroNota);
}
