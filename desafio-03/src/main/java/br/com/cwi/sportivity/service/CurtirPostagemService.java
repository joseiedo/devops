package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Curtida;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.repository.CurtidaRepository;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.NowService;
import br.com.cwi.sportivity.service.core.ValidaPostagemDisponivelParaUsuarioService;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import br.com.cwi.sportivity.service.core.ValidaPostagemJaCurtidaPorUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.PostagemResumidaMapper.toResponse;

@Service
public class CurtirPostagemService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Autowired
    private ValidaPostagemJaCurtidaPorUsuarioService validaPostagemJaCurtidaPorUsuarioService;

    @Autowired
    private ValidaPostagemDisponivelParaUsuarioService validaPostagemDisponivelParaUsuarioService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private NowService nowService;

    @Transactional
    public PostagemResumidaResponse curtir(Long postagemId) {
        validaPostagemExisteService.validar(postagemId);

        Usuario usuario = usuarioAutenticadoService.get();
        Postagem postagem = postagemRepository.findById(postagemId).get();

        validaPostagemJaCurtidaPorUsuarioService.validar(postagem);
        validaPostagemDisponivelParaUsuarioService.validar(postagem);

        Curtida curtida = new Curtida();
        curtida.setHorarioCurtida(nowService.getDateTime());
        curtida.setUsuario(usuario);
        curtida.setPostagem(postagem);


        curtidaRepository.save(curtida);

        return toResponse(postagem);
    }
}
