package br.com.cwi.sportivity.repository;

import br.com.cwi.sportivity.domain.Comentario;
import br.com.cwi.sportivity.domain.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findAllByPostagem(Postagem postagem);
}
