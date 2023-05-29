package com.br.alura.forum.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.forum.resposta.Resposta;
import com.br.alura.forum.domain.forum.resposta.RespostaRepository;
import com.br.alura.forum.domain.forum.resposta.DTO.DadosAtualizacaoResposta;
import com.br.alura.forum.domain.forum.resposta.DTO.DadosResposta;
import com.br.alura.forum.domain.forum.resposta.DTO.DetalhamentoResposta;
import com.br.alura.forum.domain.forum.topico.TopicoRepository;
import com.br.alura.forum.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoResposta> cadastrarResposta(@RequestBody @Valid DadosResposta dados, UriComponentsBuilder builder){
        var usuario = usuarioRepository.getReferenceById(dados.usuario().getId());
        var topico = topicoRepository.getReferenceById(dados.topico().getId());

        var resposta = new Resposta(dados);
        resposta.setTopico(topico);
        resposta.setUsuario(usuario);
        resposta = repository.save(resposta);

        var uri = builder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoResposta(resposta));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DetalhamentoResposta>> paginarResposta(Pageable paginacao){
        Page<DetalhamentoResposta> resposta = repository.findAllBy(paginacao).map(DetalhamentoResposta::new);

        return ResponseEntity.ok(resposta);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoResposta> atualizarResposta(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoResposta dados){
        var resposta = repository.getReferenceById(id);
        resposta.setMensagem(dados.mensagem());
        resposta = repository.save(resposta);

        return ResponseEntity.ok(new DetalhamentoResposta(resposta));
    }
}
