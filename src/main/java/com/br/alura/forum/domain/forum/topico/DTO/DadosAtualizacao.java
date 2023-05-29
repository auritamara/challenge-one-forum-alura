package com.br.alura.forum.domain.forum.topico.DTO;


import com.br.alura.forum.domain.curso.DTO.DadosCurso;

public record DadosAtualizacao (Long id, String mensagem, String titulo, DadosCurso curso ) {

}
