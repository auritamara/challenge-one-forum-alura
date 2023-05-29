package com.br.alura.forum.domain.forum.resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    Page<Resposta> findAllBy(Pageable paginacao);
}
