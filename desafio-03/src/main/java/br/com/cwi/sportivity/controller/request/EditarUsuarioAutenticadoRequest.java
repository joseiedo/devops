package br.com.cwi.sportivity.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditarUsuarioAutenticadoRequest {

    @NotBlank
    private String nome;
    private String apelido;

    private String urlImagem;
}
