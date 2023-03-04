package br.com.cwi.sportivity.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ComentarPostagemRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String conteudo;
}
