package br.com.cwi.sportivity.repository;

import br.com.cwi.sportivity.domain.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {


    @Query("SELECT p FROM Postagem p WHERE p.usuario.id = :usuarioId OR p.usuario.id IN :amigosId ORDER BY p.dataPostagem DESC")
    Page<Postagem> findByUsuarioIdAndAmigosIdOrderByData(Long usuarioId, List<Long> amigosId, Pageable pageable);
}
