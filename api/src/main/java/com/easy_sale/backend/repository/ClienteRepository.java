package com.easy_sale.backend.repository;

import com.easy_sale.backend.domain.cliente.Cliente;
import com.easy_sale.backend.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpf(String cpf);
}
