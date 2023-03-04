package br.com.cwi.sportivity.service.core;

import br.com.cwi.sportivity.domain.Postagem;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.CurtidaRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaPostagemNaoCurtidaPorUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    public void validar(Postagem postagem) {
        Usuario usuario = usuarioAutenticadoService.get();

        if(!curtidaRepository.existsByUsuarioAndPostagem(usuario, postagem)){
            throw  new ResponseStatusException(BAD_REQUEST, "Não tem como descurtir o que não foi curtido.");
        }
    }
}
