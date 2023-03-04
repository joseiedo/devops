package br.com.cwi.sportivity.controller.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
public class PostagemResumidaResponse {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private UsuarioResumidoResponse usuario;

    private String conteudo;

    private LocalDateTime dataPostagem;

    private String urlImagem;

    private int  totalCurtidas;

    private boolean isPrivado;
    private int  totalComentarios;

}
