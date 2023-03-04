package br.com.cwi.sportivity.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IncluirPostagemRequest {

    @NotBlank
    private String conteudo;

    private String urlImagem;

    @NotNull
    private Boolean isPrivado;
}
