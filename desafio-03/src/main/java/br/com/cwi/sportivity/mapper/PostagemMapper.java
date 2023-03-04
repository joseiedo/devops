package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.request.IncluirPostagemRequest;
import br.com.cwi.sportivity.domain.Postagem;

import java.util.ArrayList;

public class PostagemMapper {
    public static Postagem toEntity(IncluirPostagemRequest request) {
        return Postagem.builder()
                .conteudo(request.getConteudo())
                .urlImagem(request.getUrlImagem())
                .isPrivado(request.getIsPrivado())
                .curtidas(new ArrayList<>())
                .comentarios(new ArrayList<>())
                .build();
    }
}
