package br.com.cwi.sportivity.repository;

import br.com.cwi.sportivity.domain.Curtida;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {
    boolean existsByUsuarioAndPostagem(Usuario usuario, Postagem postagem);

    Curtida findByUsuarioAndPostagem(Usuario usuario, Postagem postagem);
}
