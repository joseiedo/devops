package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.response.DetalharUsuarioResponse;
import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class DetalharUsuarioMapper {
    public static DetalharUsuarioResponse toResponse(Usuario entity, String statusAmizadeComUsuarioAutenticado) {

        List<PostagemResumidaResponse> postagens = entity.getPostagens().stream()
                .map(PostagemResumidaMapper::toResponse)
                .collect(Collectors.toList());

        return DetalharUsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .apelido(entity.getApelido())
                .urlImagem(entity.getUrlImagem())
                .dataNascimento(entity.getDataNascimento())
                .postagens(postagens)
                .statusAmizade(statusAmizadeComUsuarioAutenticado)
                .build();
    }
}
