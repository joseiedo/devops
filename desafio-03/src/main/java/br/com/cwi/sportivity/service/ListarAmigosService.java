package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.AmizadeResumidaResponse;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.cwi.sportivity.mapper.AmizadeResumidaMapper.toResponse;

@Service
public class ListarAmigosService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;


    public Page<AmizadeResumidaResponse> listar(String texto, Pageable pageable) {
        Long usuarioID = usuarioAutenticadoService.getId();

        return amizadeRepository.getAmizadesByUsuarioId(usuarioID, texto, pageable).map((amizade) -> {

            AmizadeResumidaResponse response = toResponse(amizade);

            if(response.getIdUsuario().equals(usuarioID)){
                response.setIdUsuario(amizade.getAmigo().getId());
                response.setNomeUsuario(amizade.getAmigo().getNome());
                response.setUrlImagemUsuario(amizade.getAmigo().getUrlImagem());
            }

            return response;
        });

    }
}
