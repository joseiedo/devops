package br.com.cwi.sportivity.controller.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AmizadeResumidaResponse {

    private Long idAmizade;
    private String nomeUsuario;
    private Long idUsuario;
    private String urlImagemUsuario;

}
