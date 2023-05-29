package com.br.alura.forum.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.br.alura.forum.domain.usuario.Usuario;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    public String gerarUsuario(Usuario usuario) {
       return JWT.create()
               .withIssuer("Forum.alura")
               .withSubject(usuario.getEmail())
               .withExpiresAt(LocalDateTime.now()
                       .plusMinutes(10)
                       .toInstant(ZoneOffset.of("-03:00")))
               .sign(Algorithm.HMAC256("beachtennis"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("beachtennis"))
                .withIssuer("Forum.alura")
                .build().verify(token).getSubject();
    }
}


