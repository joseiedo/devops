package br.com.cwi.sportivity.mapper;

import br.com.cwi.sportivity.controller.response.AmizadeResumidaResponse;
import br.com.cwi.sportivity.domain.Amizade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmizadeResumidaMapper {
    public static AmizadeResumidaResponse toResponse(Amizade amizade) {
        return AmizadeResumidaResponse.builder()
                .idAmizade(amizade.getId())
                .idUsuario(amizade.getUsuario().getId())
                .urlImagemUsuario(amizade.getUsuario().getUrlImagem())
                .nomeUsuario(amizade.getUsuario().getNome())
                .build();
    }
}
