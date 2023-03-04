package br.com.cwi.sportivity.domain;

import br.com.cwi.sportivity.security.domain.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String apelido;

    private String urlImagem;

    private String senha;

    private boolean isAtivo;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario",  cascade = CascadeType.ALL)
    private List<Postagem> postagens = new ArrayList<>();

    @OneToMany(mappedBy = "usuario",  cascade = CascadeType.ALL)
    private List<Curtida> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "amigo",  cascade = CascadeType.ALL)
    private List<Amizade> solicitacoesRecebidas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario",  cascade = CascadeType.ALL)
    private List<Amizade> solicitacoesEnviadas = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }

}
