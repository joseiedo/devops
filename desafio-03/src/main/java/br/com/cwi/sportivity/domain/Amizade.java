package br.com.cwi.sportivity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amizade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime dataAmizade;

    @Enumerated(STRING)
    private StatusAmizade status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "amigo_id")
    private Usuario amigo;


    public void adicionarNovaSolicitacao(Usuario usuarioAAdicionar, Usuario usuarioAutenticado) {
        this.setUsuario(usuarioAutenticado);
        this.setAmigo(usuarioAAdicionar);
        usuarioAAdicionar.getSolicitacoesRecebidas().add(this);
        usuarioAutenticado.getSolicitacoesEnviadas().add(this);
    }
}
