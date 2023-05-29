package com.br.alura.forum.domain.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import com.br.alura.forum.domain.usuario.DTO.DadosAtualizacaoUsuario;

import com.br.alura.forum.domain.usuario.DTO.DadosUsuario;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
	private Boolean ativo;

    public Usuario(DadosUsuario autor) {
		this.ativo = true;
        this.nome = autor.nome();
        this.email = autor.email();
        this.senha = autor.senha();
    }

	public void atualizarUsuario(DadosAtualizacaoUsuario dados) {
		
		if (dados.nome() != null){
			this.nome = dados.nome();
		}
		if (dados.email() != null){
			this.email = dados.email();
		}
		if (dados.senha() != null){
			this.senha = dados.senha();
		}
		
	}

	public void excluir() {
		this.ativo = false;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
	
	

	

}


