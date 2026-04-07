package com.easysale.backend.repository;

import com.easysale.backend.domain.venda.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    NotaFiscal findByNumeroNota(Long numeroNota);
}
