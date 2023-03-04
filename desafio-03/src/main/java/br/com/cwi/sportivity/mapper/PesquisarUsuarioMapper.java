package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.response.PesquisarUsuarioResponse;
import br.com.cwi.sportivity.domain.Usuario;


public class PesquisarUsuarioMapper {


    public static PesquisarUsuarioResponse toResponse(Usuario entity) {
        return PesquisarUsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .urlImagem(entity.getUrlImagem())
                .build();
    }
}
