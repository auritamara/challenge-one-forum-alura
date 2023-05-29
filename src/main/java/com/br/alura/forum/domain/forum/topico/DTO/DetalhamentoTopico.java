package com.br.alura.forum.domain.forum.topico.DTO;

import java.time.LocalDateTime;

import com.br.alura.forum.domain.curso.Curso;

import com.br.alura.forum.domain.forum.topico.Status;
import com.br.alura.forum.domain.forum.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;


public record DetalhamentoTopico(Long id, String titulo, String mensagem, Status status, LocalDateTime data_criacao, Usuario usuario, Curso curso) {
    public DetalhamentoTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensagem(), topico.getStatus(), topico.getDataCriacao(), topico.getUsuario(), topico.getCurso());
    }

}
