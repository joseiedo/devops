package br.com.cwi.sportivity.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResumidoResponse {
    private Long id;

    private String nome;

    private String apelido;

    private String urlImagem;

    private String email;
}
