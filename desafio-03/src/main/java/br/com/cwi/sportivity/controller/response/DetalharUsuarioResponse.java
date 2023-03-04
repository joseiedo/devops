package br.com.cwi.sportivity.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DetalharUsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String apelido;

    private String urlImagem;

    private LocalDate dataNascimento;

    private List<PostagemResumidaResponse> postagens;
    private String statusAmizade;
}
