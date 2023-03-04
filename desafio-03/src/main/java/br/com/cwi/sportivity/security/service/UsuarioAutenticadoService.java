package br.com.cwi.sportivity.security.service;

import br.com.cwi.sportivity.controller.response.UsuarioResumidoResponse;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.mapper.UsuarioResumidoMapper;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.config.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UsuarioSecurity) {
            return ((UsuarioSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public Usuario get() {
        Long id = getId();

        if (isNull(id)) {
            return null;
        }

        return usuarioRepository.findById(getId()).orElse(null);
    }

    public UsuarioResumidoResponse getResponse() {
        Usuario entity = get();
        return nonNull(entity) ? UsuarioResumidoMapper.toResponse(entity) : new UsuarioResumidoResponse();
    }
}
