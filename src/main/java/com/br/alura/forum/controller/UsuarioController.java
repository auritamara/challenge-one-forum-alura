package com.br.alura.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.br.alura.forum.domain.usuario.Usuario;
import com.br.alura.forum.domain.usuario.UsuarioRepository;
import com.br.alura.forum.domain.usuario.DTO.DadosAtualizacaoUsuario;
import com.br.alura.forum.domain.usuario.DTO.DadosUsuario;
import com.br.alura.forum.domain.usuario.DTO.DetalhamentoUsuario;
import com.br.alura.forum.domain.usuario.DTO.ListagemUsuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder encoder;

    

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoUsuario> cadastrarUsuario(@RequestBody @Valid DadosUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        usuario.setSenha(encoder.encode(dados.senha()));
        

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoUsuario(usuario));
    
    }

    @GetMapping
    public ResponseEntity<Page<ListagemUsuario>> listarUsuario(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = usuarioRepository.findAllByAtivoTrue(paginacao).map(ListagemUsuario::new);
         
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoUsuario> atualizarUsuario(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizarUsuario(dados);

        return ResponseEntity.ok(new DetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoUsuario> detalharUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        
        return ResponseEntity.ok(new DetalhamentoUsuario(usuario));
    }
}
