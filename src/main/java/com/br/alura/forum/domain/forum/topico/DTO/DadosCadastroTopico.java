package com.br.alura.forum.domain.forum.topico.DTO;


import com.br.alura.forum.domain.curso.DTO.DadosCurso;
import com.br.alura.forum.domain.forum.topico.Status;
import com.br.alura.forum.domain.usuario.DTO.DadosUsuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroTopico(@NotBlank String titulo, @NotBlank String mensagem, Status status, @NotNull @Valid DadosUsuario autor, @NotNull @Valid DadosCurso curso) {
}
