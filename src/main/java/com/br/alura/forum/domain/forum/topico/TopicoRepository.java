package com.br.alura.forum.domain.forum.topico;





import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByAtivoTrue(Pageable paginacao);
    
}
