package br.com.cwi.sportivity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Postagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String conteudo;

    private LocalDateTime dataPostagem;

    private String urlImagem;
    @OneToMany(mappedBy = "postagem", cascade = ALL)
    private List<Curtida> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "postagem", cascade = ALL)
    private List<Comentario> comentarios = new ArrayList<>();
    private boolean isPrivado;


}
