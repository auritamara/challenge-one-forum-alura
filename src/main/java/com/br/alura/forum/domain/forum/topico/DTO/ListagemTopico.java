package com.br.alura.forum.domain.forum.topico.DTO;

import com.br.alura.forum.domain.forum.topico.Topico;

import com.br.alura.forum.domain.curso.Curso;

public record ListagemTopico(Long id, String titulo, String mensagem, Curso curso) {
    public ListagemTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensagem(), topico.getCurso());
    }

}
