package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaUsuarioAceitandoSolicitacaoIgualUsuarioAutenticadoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;


    public void validar(Amizade amizade) {
        if(!Objects.equals(amizade.getAmigo().getId(), usuarioAutenticadoService.getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possivel aceitar uma solicitação de amizade que não foi enviada para você");
        }
    }
}
