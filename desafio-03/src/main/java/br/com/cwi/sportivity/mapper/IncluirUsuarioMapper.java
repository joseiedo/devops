package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.request.IncluirUsuarioRequest;
import br.com.cwi.sportivity.controller.response.IncluirUsuarioResponse;
import br.com.cwi.sportivity.domain.Usuario;

public class IncluirUsuarioMapper {

    public static Usuario toEntity(IncluirUsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        return entity;
    }

    public static IncluirUsuarioResponse toResponse(Usuario entity) {
        IncluirUsuarioResponse response = new IncluirUsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        return response;
    }
}
