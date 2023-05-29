package com.br.alura.forum.domain.forum.resposta.DTO;


import com.br.alura.forum.domain.forum.resposta.Resposta;
import com.br.alura.forum.domain.forum.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;

public record DetalhamentoResposta(Long id, String mensagem, Topico topico, Usuario usuario, Boolean solucao) {

    public DetalhamentoResposta(Resposta dados){
        this(dados.getId(), dados.getMensagem(), dados.getTopico(), dados.getUsuario(), dados.getSolucao());
    }
}