package br.com.cwi.sportivity.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioResponse {

    private Long id;
    private String nomeUsuario;

    private String urlFotoUsuario;

    private Long idUsuario;

    private Long idPostagem;

    private String conteudo;
    private LocalDateTime dataCriacao;
}
