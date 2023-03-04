package br.com.cwi.sportivity.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncluirUsuarioResponse {

    private Long id;
    private String nome;
    private String email;
}
