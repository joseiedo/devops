package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.PesquisarUsuarioResponse;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static br.com.cwi.sportivity.mapper.PesquisarUsuarioMapper.toResponse;

@Service
public class ListarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<PesquisarUsuarioResponse> pesquisar(String texto, Pageable pageable) {
        Long idUsuarioAutenticado = usuarioAutenticadoService.getId();

        return usuarioRepository.findByEmailContainingOrNomeContainingAllIgnoreCase(texto, texto, pageable)
                .map((usuario) -> {
                    List<Long> listaAmigos = amizadeRepository.getAmigosIdByUsuarioId(usuario.getId());
                    PesquisarUsuarioResponse response = toResponse(usuario);

                    Boolean isAmigo = listaAmigos.contains(idUsuarioAutenticado);
                    Boolean isUsuarioAutenticado = Objects.equals(usuario.getId(), idUsuarioAutenticado);

                    response.setIsAmigo(isAmigo);
                    response.setIsUsuarioAutenticado(isUsuarioAutenticado);
                    return response;
                });
    }
}
