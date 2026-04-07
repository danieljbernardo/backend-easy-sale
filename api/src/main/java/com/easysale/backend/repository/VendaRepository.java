package com.easysale.backend.repository;

import com.easysale.backend.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Venda findByIdVenda(Long id);

    @Query("select v from Venda v where (:cpf is null or v.cliente.cpf= :cpf) and (:data is null or v.dataVenda= :data)")
    ArrayList<Venda> findByCpfAndData(@Param("cpf") String cpf, @Param("data") LocalDate data);
}
