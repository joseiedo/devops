package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class ValidaPostagemPertenceAoUsuario {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void validar(String emailAutor) {
        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        String emailAutenticado = usuarioAutenticado.getEmail();

        if(!emailAutenticado.equals(emailAutor)){
            throw new ResponseStatusException(UNAUTHORIZED, "Você não está autorizado para essa ação.");
        }
    }
}
