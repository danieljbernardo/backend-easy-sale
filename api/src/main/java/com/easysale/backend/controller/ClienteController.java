package com.easysale.backend.controller;

import com.easysale.backend.domain.CpfDTO;
import com.easysale.backend.domain.cliente.ClienteDTO;
import com.easysale.backend.domain.cliente.EditarClienteDTO;
import com.easysale.backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/easysale/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/cadastrar-cliente")
    public ResponseEntity cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO){
        return this.clienteService.cadastrandoCliente(clienteDTO);
    }

    @DeleteMapping("/deletar-cliente")
    public ResponseEntity deletarCliente(@RequestBody @Valid CpfDTO cpfDTO){
        return this.clienteService.deletandoCliente(cpfDTO.cpf());
    }

    @PostMapping("/buscar-cliente")
    public ResponseEntity buscarCliente(@RequestBody @Valid CpfDTO cpfDTO){
        return this.clienteService.buscandoCliente(cpfDTO.cpf());
    }

    @PatchMapping("/editar-cliente")
    public ResponseEntity editarCliente(@RequestBody @Valid EditarClienteDTO editarClienteDTO){
        return this.clienteService.editandoCliente(editarClienteDTO);
    }
}
