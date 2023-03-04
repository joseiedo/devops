package br.com.cwi.sportivity.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PesquisarUsuarioResponse {

    private Long id;

    private Boolean isAmigo;

    private String nome;

    private String urlImagem;

    private Boolean isUsuarioAutenticado;
}
