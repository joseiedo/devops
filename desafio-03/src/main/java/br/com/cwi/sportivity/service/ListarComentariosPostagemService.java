package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.ComentarioResponse;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.mapper.ComentarioMapper;
import br.com.cwi.sportivity.repository.ComentariosRepository;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.service.core.ValidaPostagemDisponivelParaUsuarioService;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarComentariosPostagemService {

    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Autowired
    private ValidaPostagemDisponivelParaUsuarioService validaPostagemDisponivelParaUsuarioService;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ComentariosRepository comentariosRepository;

    public List<ComentarioResponse> listar(Long id) {
        validaPostagemExisteService.validar(id);

        Postagem postagem = postagemRepository.findById(id).get();
        validaPostagemDisponivelParaUsuarioService.validar(postagem);

        return comentariosRepository.findAllByPostagem(postagem).stream()
                .map(ComentarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
