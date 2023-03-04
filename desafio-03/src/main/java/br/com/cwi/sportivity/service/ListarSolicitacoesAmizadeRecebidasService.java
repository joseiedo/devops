package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.AmizadeResumidaResponse;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.mapper.AmizadeResumidaMapper;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.cwi.sportivity.domain.StatusAmizade.PENDENTE;

@Service
public class ListarSolicitacoesAmizadeRecebidasService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<AmizadeResumidaResponse> listar(Pageable pageable) {
        Usuario usuario = usuarioAutenticadoService.get();

        return amizadeRepository.findByAmigoEqualsAndStatusEquals(usuario, PENDENTE, pageable)
                .map(AmizadeResumidaMapper::toResponse);
    }
}
