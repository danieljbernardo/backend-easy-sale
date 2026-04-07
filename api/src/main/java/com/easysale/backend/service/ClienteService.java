package com.easysale.backend.service;

import com.easysale.backend.domain.cliente.Cliente;
import com.easysale.backend.domain.cliente.ClienteDTO;
import com.easysale.backend.domain.cliente.EditarClienteDTO;
import com.easysale.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity cadastrandoCliente(ClienteDTO clienteDTO){
        if (this.clienteRepository.findByCpf(clienteDTO.cpf())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente já cadastrado no sistema");
        }
        Cliente cliente=new Cliente(clienteDTO.nome(),clienteDTO.cpf(), clienteDTO.telefone());
        this.clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deletandoCliente(String cpf){
        if (this.clienteRepository.findByCpf(cpf)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O Cliente já não existe no sistema");
        }
        Cliente cliente=this.clienteRepository.findByCpf(cpf);
        this.clienteRepository.delete(cliente);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity buscandoCliente(String cpf){
        if (this.clienteRepository.findByCpf(cpf)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado no sistema");
        }
        Cliente cliente=this.clienteRepository.findByCpf(cpf);
        return ResponseEntity.ok().body(new ClienteDTO(cliente.getNome(), cliente.getCpf(), cliente.getTelefone()));
    }

    public ResponseEntity editandoCliente(EditarClienteDTO editarClienteDTO){
            Cliente cliente=this.clienteRepository.findByCpf(editarClienteDTO.cpf());
            if(!editarClienteDTO.nome().isBlank()){
                cliente.setNome(editarClienteDTO.nome());
            }
           if(!editarClienteDTO.cpf().isBlank()){
               cliente.setCpf(editarClienteDTO.cpf());
           }
           if(!editarClienteDTO.telefone().isBlank()){
               cliente.setTelefone(editarClienteDTO.telefone());
           }

        this.clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

}
