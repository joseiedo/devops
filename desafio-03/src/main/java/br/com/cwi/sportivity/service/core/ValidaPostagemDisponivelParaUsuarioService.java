package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaPostagemDisponivelParaUsuarioService {


    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void validar(Postagem postagem) {

        if(!postagem.isPrivado()) return;
        Long idUsuario = usuarioAutenticadoService.getId();

        if(postagem.getUsuario().getId().equals(idUsuario)) return;

        List<Long> amigosDoAutorDoPost = amizadeRepository.getAmigosIdByUsuarioId(postagem.getUsuario().getId());
        if(!amigosDoAutorDoPost.contains(idUsuario)){
            throw new ResponseStatusException(BAD_REQUEST, "Não é possivel curtir uma postagem indisponivel para você.");
        }
    }
}
