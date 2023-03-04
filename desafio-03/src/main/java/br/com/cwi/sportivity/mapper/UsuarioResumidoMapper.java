package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.response.UsuarioResumidoResponse;
import br.com.cwi.sportivity.domain.Usuario;

public class UsuarioResumidoMapper {
    public static UsuarioResumidoResponse toResponse(Usuario entity) {
        return UsuarioResumidoResponse.builder()
                .id(entity.getId())
                .urlImagem(entity.getUrlImagem())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .apelido(entity.getApelido())
                .build();
    }
}
