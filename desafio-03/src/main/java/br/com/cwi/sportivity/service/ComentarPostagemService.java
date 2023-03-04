package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.request.ComentarPostagemRequest;
import br.com.cwi.sportivity.controller.response.ComentarioResponse;
import br.com.cwi.sportivity.domain.Comentario;
import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.mapper.ComentarioMapper;
import br.com.cwi.sportivity.repository.ComentariosRepository;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.NowService;
import br.com.cwi.sportivity.service.core.ValidaPostagemDisponivelParaUsuarioService;
import br.com.cwi.sportivity.service.core.ValidaPostagemExisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.ComentarioMapper.toResponse;

@Service
public class ComentarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private ValidaPostagemExisteService validaPostagemExisteService;

    @Autowired
    private ValidaPostagemDisponivelParaUsuarioService validaPostagemDisponivelParaUsuarioService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ComentariosRepository comentariosRepository;
    @Autowired
    private NowService nowService;

    @Transactional
    public ComentarioResponse comentar(Long idPostagem, ComentarPostagemRequest request) {
        validaPostagemExisteService.validar(idPostagem);

        Postagem postagem = postagemRepository.findById(idPostagem).get();
        Usuario usuario = usuarioAutenticadoService.get();

        validaPostagemDisponivelParaUsuarioService.validar(postagem);

        Comentario comentario = ComentarioMapper.toEntity(request);
        comentario.setPostagem(postagem);
        comentario.setUsuario(usuario);
        comentario.setDataCriacao(nowService.getDateTime());

        postagem.getComentarios().add(comentario);

        comentariosRepository.save(comentario);

        return toResponse(comentario);
    }
}
