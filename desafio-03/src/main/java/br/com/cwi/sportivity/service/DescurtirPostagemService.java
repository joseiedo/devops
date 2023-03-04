package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Curtida;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.CurtidaRepository;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.NowService;
import br.com.cwi.sportivity.service.core.ValidaPostagemDisponivelParaUsuarioService;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import br.com.cwi.sportivity.service.core.ValidaPostagemNaoCurtidaPorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.PostagemResumidaMapper.toResponse;

@Service
public class DescurtirPostagemService {


    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ValidaPostagemDisponivelParaUsuarioService validaPostagemDisponivelParaUsuarioService;

    @Autowired
    private NowService nowService;

    @Autowired
    private ValidaPostagemNaoCurtidaPorUsuarioService validaPostagemNaoCurtidaPorUsuarioService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Transactional
    public PostagemResumidaResponse descurtir(Long postagemId) {
        validaPostagemExisteService.validar(postagemId);

        Usuario usuario = usuarioAutenticadoService.get();
        Postagem postagem = postagemRepository.findById(postagemId).get();

        validaPostagemNaoCurtidaPorUsuarioService.validar(postagem);
        validaPostagemDisponivelParaUsuarioService.validar(postagem);

        Curtida curtida = curtidaRepository.findByUsuarioAndPostagem(usuario, postagem);

        curtidaRepository.delete(curtida);
        postagem.getCurtidas().remove(curtida);

        return toResponse(postagem);
    }
}
