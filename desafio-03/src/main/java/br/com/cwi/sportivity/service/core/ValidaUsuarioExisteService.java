package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaUsuarioExisteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw new ResponseStatusException(NOT_FOUND,"Usuário não encontrado.");
        }
    }
}
