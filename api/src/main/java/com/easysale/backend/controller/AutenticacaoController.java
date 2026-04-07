package com.easysale.backend.controller;

import com.easy_sale.backend.domain.usuario.*;
import com.easysale.backend.domain.usuario.AutenticacaoDTO;
import com.easysale.backend.domain.usuario.UsuarioDTO;
import com.easysale.backend.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/easysale/autenticacao")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid @AuthenticationPrincipal AutenticacaoDTO autenticacaoDTO){
       return this.autenticacaoService.fazendoLogin(autenticacaoDTO);
    }

   @PostMapping("/cadastrar")
   public ResponseEntity cadastrar(@RequestBody @Valid @AuthenticationPrincipal UsuarioDTO usuarioDTO){
       return this.autenticacaoService.fazendoCadastro(usuarioDTO);
   }
}
