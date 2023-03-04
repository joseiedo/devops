package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaAmizadeExisteService {

    @Autowired
    private AmizadeRepository amizadeRepository;


    public void validar(Long idSolicitacao) {
        if(!amizadeRepository.existsById(idSolicitacao)){
            throw new ResponseStatusException(BAD_REQUEST, "Solicitação de amizade não encontrada.");
        }
    }
}
