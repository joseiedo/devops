package br.com.cwi.sportivity.mapper;


import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.controller.response.UsuarioResumidoResponse;
import br.com.cwi.sportivity.domain.Postagem;

public class PostagemResumidaMapper {
    public static PostagemResumidaResponse toResponse(Postagem entity) {
        UsuarioResumidoResponse usuario = UsuarioResumidoMapper.toResponse(entity.getUsuario());

        return PostagemResumidaResponse.builder()
                .id(entity.getId())
                .usuario(usuario)
                .urlImagem(entity.getUrlImagem())
                .conteudo(entity.getConteudo())
                .dataPostagem(entity.getDataPostagem())
                .totalCurtidas(entity.getCurtidas().size())
                .totalComentarios(entity.getComentarios().size())
                .isPrivado(entity.isPrivado())
                .build();
    }
}
