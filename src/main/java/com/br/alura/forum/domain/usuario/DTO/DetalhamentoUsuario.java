package com.br.alura.forum.domain.usuario.DTO;

import com.br.alura.forum.domain.usuario.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DetalhamentoUsuario(Long id, @NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha) {

    public DetalhamentoUsuario(Usuario cadastro){
        this(cadastro.getId(), cadastro.getNome(), cadastro.getEmail(), cadastro.getSenha());
    }

}
