package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaUsuarioAAdicionarDiferenteDoAutenticadoService {

    public void validar(Usuario usuarioAAdicionar, Usuario usuarioAutenticado) {
        if(Objects.equals(usuarioAAdicionar.getId(), usuarioAutenticado.getId())){
            throw  new ResponseStatusException(BAD_REQUEST, "Não é possivel adicionar a si mesmo.");
        }
    }
}
