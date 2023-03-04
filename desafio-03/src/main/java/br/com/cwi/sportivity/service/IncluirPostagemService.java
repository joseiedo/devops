package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.request.IncluirPostagemRequest;
import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.mapper.PostagemMapper;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.PostagemResumidaMapper.toResponse;

@Service
public class IncluirPostagemService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private NowService nowService;


    @Transactional
    public PostagemResumidaResponse incluir(IncluirPostagemRequest request) {

        Postagem postagem = PostagemMapper.toEntity(request);
        Usuario usuario = usuarioAutenticadoService.get();

        postagem.setDataPostagem(nowService.getDateTime());
        postagem.setUsuario(usuario);

        postagemRepository.save(postagem);

        return toResponse(postagem);
    }

}
