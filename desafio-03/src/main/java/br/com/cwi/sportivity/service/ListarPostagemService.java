package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.mapper.PostagemResumidaMapper;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.repository.PostagemRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<PostagemResumidaResponse> listar(Pageable pageable) {
        Usuario usuario = usuarioAutenticadoService.get();

        List<Long> amigosDoUsuario = amizadeRepository.getAmigosIdByUsuarioId(usuario.getId());

        return postagemRepository.findByUsuarioIdAndAmigosIdOrderByData(usuario.getId(), amigosDoUsuario, pageable)
                .map(PostagemResumidaMapper::toResponse);
    }


}
