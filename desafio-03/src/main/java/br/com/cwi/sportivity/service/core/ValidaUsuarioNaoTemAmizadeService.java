package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaUsuarioNaoTemAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void validar(Usuario usuario) {
        Long idUsuarioAutenticado = usuarioAutenticadoService.getId();
        List<Long> listaAmigos = amizadeRepository.getAmigosIdByUsuarioId(usuario.getId());

        if(listaAmigos.contains(idUsuarioAutenticado)) {
            throw new ResponseStatusException(BAD_REQUEST, "Vocês já são amigos.");
        }
    }
}
