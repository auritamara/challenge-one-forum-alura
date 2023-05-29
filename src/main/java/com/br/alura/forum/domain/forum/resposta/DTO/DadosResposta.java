package com.br.alura.forum.domain.forum.resposta.DTO;

import com.br.alura.forum.domain.forum.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosResposta (@NotBlank String mensagem, @NotNull Topico topico, @NotNull Usuario usuario, Boolean solucao) {
}
