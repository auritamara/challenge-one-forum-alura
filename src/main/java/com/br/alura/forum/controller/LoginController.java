package com.br.alura.forum.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.alura.forum.domain.usuario.Usuario;
import com.br.alura.forum.domain.usuario.DTO.DadosAutenticacao;
import com.br.alura.forum.infra.security.TokenService;


@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public String login (@RequestBody @Valid DadosAutenticacao dados){
        var user = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        var authenticate = this.manager.authenticate(user);

        var usuario = (Usuario) authenticate.getPrincipal();

        return tokenService.gerarUsuario(usuario);
    }

}




