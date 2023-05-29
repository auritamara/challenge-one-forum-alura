package com.br.alura.forum.domain.forum.resposta;

import java.time.LocalDateTime;

import com.br.alura.forum.domain.forum.resposta.DTO.DadosResposta;
import com.br.alura.forum.domain.forum.topico.Topico;
import com.br.alura.forum.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;


@Entity(name = "Resposta")
@Table(name = "respostas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@ManyToOne(fetch = FetchType.LAZY)
	private Topico topico;
	private LocalDateTime dataCriacao;
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	private Boolean solucao = false;

	public Resposta(DadosResposta dados) {
		this.mensagem = dados.mensagem();
		this.topico = dados.topico();
		this.dataCriacao = LocalDateTime.now();
		this.usuario = dados.usuario();
		this.solucao = dados.solucao();
	}

}
