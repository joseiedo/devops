package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.request.ComentarPostagemRequest;
import br.com.cwi.sportivity.controller.response.ComentarioResponse;
import br.com.cwi.sportivity.domain.Comentario;

public class ComentarioMapper {
    public static Comentario toEntity(ComentarPostagemRequest request) {
        return Comentario.builder()
                .conteudo(request.getConteudo())
                .build();
    }

    public static ComentarioResponse toResponse(Comentario comentario) {
        return ComentarioResponse.builder()
                .id(comentario.getId())
                .idUsuario(comentario.getUsuario().getId())
                .conteudo(comentario.getConteudo())
                .nomeUsuario(comentario.getUsuario().getNome())
                .urlFotoUsuario(comentario.getUsuario().getUrlImagem())
                .idPostagem(comentario.getPostagem().getId())
                .dataCriacao(comentario.getDataCriacao())
                .build();
    }
}
