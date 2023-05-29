package com.br.alura.forum.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.curso.CursoRepository;
import com.br.alura.forum.domain.forum.topico.Topico;
import com.br.alura.forum.domain.forum.topico.TopicoRepository;
import com.br.alura.forum.domain.forum.topico.DTO.DadosAtualizacao;
import com.br.alura.forum.domain.forum.topico.DTO.DadosCadastroTopico;
import com.br.alura.forum.domain.forum.topico.DTO.DetalhamentoTopico;
import com.br.alura.forum.domain.forum.topico.DTO.ListagemTopico;
import com.br.alura.forum.domain.usuario.Usuario;
import com.br.alura.forum.domain.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoTopico> cadastrarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
       var usuario = new Usuario(dados.autor());
        usuarioRepository.save(usuario);

        var curso = new Curso(dados.curso());
        cursoRepository.save(curso);

        var topico = new Topico(dados);
        topico.setCurso(curso);
        topico.setUsuario(usuario);
        topicoRepository.save(topico); 

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemTopico>> listarTopico(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = topicoRepository.findAllByAtivoTrue(paginacao).map(ListagemTopico::new);
         
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoTopico> atualizarTopico(@RequestBody @Valid DadosAtualizacao dados){
        var topico = topicoRepository.getReferenceById(dados.id());
        topico.atualizarTopico(dados);

        return ResponseEntity.ok(new DetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoTopico> detalharTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        
        return ResponseEntity.ok(new DetalhamentoTopico(topico));
    }

    

}
