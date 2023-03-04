package br.com.cwi.sportivity.repository;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.domain.StatusAmizade;
import br.com.cwi.sportivity.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    @Query("SELECT CASE WHEN a.usuario.id = :usuarioId " +
            "THEN a.amigo.id ELSE a.usuario.id END " +
            "FROM Amizade a WHERE (a.usuario.id = :usuarioId OR a.amigo.id = :usuarioId) AND a.status = 'ACEITO'")
    List<Long> getAmigosIdByUsuarioId(Long usuarioId);

    @Query("SELECT a FROM Amizade a WHERE a.status = 'ACEITO' " +
            "AND ((LOWER(a.usuario.nome) LIKE LOWER(CONCAT('%', :text, '%')) " +
            "OR LOWER(a.usuario.email) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "OR (LOWER(a.amigo.nome) LIKE LOWER(CONCAT('%', :text, '%')) " +
            "OR LOWER(a.amigo.email) LIKE LOWER(CONCAT('%', :text, '%')))) " +
            "AND (a.usuario.id = :usuarioId OR a.amigo.id = :usuarioId)")
    Page<Amizade> getAmizadesByUsuarioId(Long usuarioId, String text, Pageable pageable);

    Page<Amizade> findByAmigoEqualsAndStatusEquals(Usuario amigo, StatusAmizade status, Pageable pageable);


}
