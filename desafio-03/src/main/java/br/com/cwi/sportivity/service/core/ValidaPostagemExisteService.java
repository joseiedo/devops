package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaPostagemExisteService {

    @Autowired
    private PostagemRepository postagemRepository;

    public void validar(Long postId) {
        if(!postagemRepository.existsById(postId)){
            throw new ResponseStatusException(BAD_REQUEST, "Postagem não existe");
        }
    }
}
