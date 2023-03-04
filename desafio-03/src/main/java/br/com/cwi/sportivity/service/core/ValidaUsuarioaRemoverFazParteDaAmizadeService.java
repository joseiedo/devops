package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaUsuarioaRemoverFazParteDaAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void validar(Amizade amizade) {
        Long usuarioId = usuarioAutenticadoService.getId();

        if(!amizade.getUsuario().getId().equals(usuarioId) && !amizade.getAmigo().getId().equals(usuarioId)){
            throw  new ResponseStatusException(BAD_REQUEST,"Não é possivel remover uma amizade que não é sua.");
        }
    }
}
