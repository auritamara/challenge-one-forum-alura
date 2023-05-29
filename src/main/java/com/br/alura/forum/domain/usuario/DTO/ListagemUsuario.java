package com.br.alura.forum.domain.usuario.DTO;

import com.br.alura.forum.domain.usuario.Usuario;

public record ListagemUsuario (Long id, String nome, String email) {
    public ListagemUsuario(Usuario dados){
        this(dados.getId(), dados.getNome(), dados.getEmail());
    }  
}
