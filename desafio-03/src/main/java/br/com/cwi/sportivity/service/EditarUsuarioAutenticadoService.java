package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.request.EditarUsuarioAutenticadoRequest;
import br.com.cwi.sportivity.controller.response.UsuarioResumidoResponse;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.UsuarioResumidoMapper.toResponse;

@Service
public class EditarUsuarioAutenticadoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResumidoResponse editar(EditarUsuarioAutenticadoRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.setNome(request.getNome());
        usuario.setApelido(request.getApelido());
        usuario.setUrlImagem(request.getUrlImagem());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
