package com.br.alura.forum.domain.usuario.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosUsuario (@NotBlank String nome, @NotBlank @Email String email, @NotBlank String senha) {
}