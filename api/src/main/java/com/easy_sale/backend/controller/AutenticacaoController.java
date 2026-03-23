package com.easy_sale.backend.controller;

import com.easy_sale.backend.domain.usuario.AutenticacaoDTO;
import com.easy_sale.backend.domain.usuario.CadastroDTO;
import com.easy_sale.backend.domain.usuario.Usuario;
import com.easy_sale.backend.domain.usuario.UsuarioRole;
import com.easy_sale.backend.infra.security.TokenService;
import com.easy_sale.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AutenticacaoDTO autenticacaoDTO){
        var emailSenha=new UsernamePasswordAuthenticationToken(autenticacaoDTO.email(), autenticacaoDTO.senha());
        var autenticacao=this.authenticationManager.authenticate(emailSenha);

        var token=this.tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok().body(token);
    }

   @PostMapping("/cadastrar")
   public ResponseEntity cadastrar(@RequestBody CadastroDTO cadastroDTO){
       if(this.usuarioRepository.findByEmail(cadastroDTO.email())!=null) return ResponseEntity.badRequest().build();

       String senhaCriptografada=new BCryptPasswordEncoder().encode(cadastroDTO.senha());

       UsuarioRole usuarioRole=UsuarioRole.valueOf(cadastroDTO.role().toUpperCase());

       Usuario usuario=new Usuario(cadastroDTO.nome(), cadastroDTO.email(), senhaCriptografada, cadastroDTO.cpf(), usuarioRole);
       this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
   }
}
