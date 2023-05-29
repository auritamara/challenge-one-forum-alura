package com.br.alura.forum.domain.curso;



import com.br.alura.forum.domain.curso.DTO.DadosCurso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(DadosCurso curso) {
        this.nome = curso.nome();
        this.categoria = curso.categoria();
    }

    public void atualizarCurso(DadosCurso dados) {
		if (dados.nome() != null){
			this.nome = dados.nome();
		}
		if (dados.categoria() != null){
			this.categoria = dados.categoria();
		}
    }
	
	

}


