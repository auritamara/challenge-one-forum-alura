package com.br.alura.forum.domain.forum.topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.br.alura.forum.domain.curso.Curso;
import com.br.alura.forum.domain.forum.resposta.Resposta;
import com.br.alura.forum.domain.forum.topico.DTO.DadosAtualizacao;
import com.br.alura.forum.domain.forum.topico.DTO.DadosCadastroTopico;

import com.br.alura.forum.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinColumn(name="autor")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="curso")
	private Curso curso;
	@OneToMany
	private List<Resposta> respostas = new ArrayList<>();
	private Boolean ativo;

	public Topico(DadosCadastroTopico cadastro) {
		this.ativo = true;
		this.dataCriacao = LocalDateTime.now();
		this.mensagem = cadastro.mensagem();
		this.titulo = cadastro.titulo();
		this.status = cadastro.status();
		
	}

	public void atualizarTopico(DadosAtualizacao dados) {
		
		if (dados.titulo() != null){
			this.titulo = dados.titulo();
		}
		if (dados.mensagem() != null){
			this.mensagem = dados.mensagem();
		}
		if (dados.curso() != null){
			this.curso.atualizarCurso(dados.curso());
		}
		
	}

    public void excluir() {
		this.ativo = false;
		this.status = Status.FECHADO;
    }

	public Topico(Resposta dados){
		this.respostas.add(dados);
	}

}
