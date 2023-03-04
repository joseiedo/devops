package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoverPostagemService {

    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Autowired
    private PostagemRepository postagemRepository;

    @Transactional
    public void remover(Long id) {
        validaPostagemExisteService.validar(id);
        postagemRepository.deleteById(id);
    }
}
