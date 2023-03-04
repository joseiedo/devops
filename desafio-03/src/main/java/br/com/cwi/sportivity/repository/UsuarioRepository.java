package br.com.cwi.sportivity.repository;

import br.com.cwi.sportivity.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Usuario> findByEmailContainingOrNomeContainingAllIgnoreCase(String texto, String texto1, Pageable pageable);
}
