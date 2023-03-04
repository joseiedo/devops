package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.StatusAmizade;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaStatusAmizadePendenteService {
    public void validar(StatusAmizade status) {
        if(status != StatusAmizade.PENDENTE) {
            throw new ResponseStatusException(BAD_REQUEST,"Amizade precisa ter sido solicitada para ser aceita.");
        }
    }
}
