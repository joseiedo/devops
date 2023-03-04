package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.request.EditarPostagemRequest;
import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import br.com.cwi.sportivity.service.core.ValidaPostagemPertenceAoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.PostagemResumidaMapper.toResponse;

@Service
public class EditarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ValidaPostagemPertenceAoUsuario validaPostagemPertenceAoUsuario;

    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Transactional
    public PostagemResumidaResponse editar(Long id, EditarPostagemRequest request) {
        validaPostagemExisteService.validar(id);

        Postagem postagem = postagemRepository.findById(id).get();
        validaPostagemPertenceAoUsuario.validar(postagem.getUsuario().getEmail());

        postagem.setConteudo(request.getConteudo());
        postagem.setUrlImagem(request.getUrlImagem());
        postagem.setPrivado(request.getIsPrivado());

        postagemRepository.save(postagem);

        return toResponse(postagem);
    }
}
