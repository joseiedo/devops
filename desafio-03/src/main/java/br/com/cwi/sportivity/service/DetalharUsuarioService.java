package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.response.DetalharUsuarioResponse;
import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioExisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.cwi.sportivity.mapper.DetalharUsuarioMapper.toResponse;

@Service
public class DetalharUsuarioService {

    public static final String NAO_ADICIONADO_STATUS = "NAO_ADICIONADO";
    @Autowired
    private ValidaUsuarioExisteService validaUsuarioExisteService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public DetalharUsuarioResponse detalhar(Long id) {
        validaUsuarioExisteService.validar(id);
        Usuario usuarioADetalhar = usuarioRepository.findById(id).get();
        Long usuarioAutenticadoId = usuarioAutenticadoService.getId();

        String status = verificaSeUsuarioEhAmigo(usuarioADetalhar, usuarioAutenticadoId);
        DetalharUsuarioResponse detalharUsuarioResponse = toResponse(usuarioADetalhar, status);

        List<PostagemResumidaResponse> postagens = listarPostagensDisponiveis(usuarioADetalhar, usuarioAutenticadoId,
                                                                                            detalharUsuarioResponse);

        detalharUsuarioResponse.setPostagens(postagens);

        return detalharUsuarioResponse;
    }

    private List<PostagemResumidaResponse> listarPostagensDisponiveis(Usuario usuarioADetalhar, Long usuarioAutenticadoId,
                                                                        DetalharUsuarioResponse detalharUsuarioResponse) {
        return detalharUsuarioResponse.getPostagens().stream().filter((post) -> {
            List<Long> amigosId = amizadeRepository.getAmigosIdByUsuarioId(usuarioADetalhar.getId());

            return !post.isPrivado() || amigosId.contains(usuarioAutenticadoId);
        }).collect(Collectors.toList());
    }


    private String verificaSeUsuarioEhAmigo(Usuario usuario, Long usuarioAutenticadoId) {

        if (usuario.getId().equals(usuarioAutenticadoId)) return null;

        Optional<Amizade> solicitacaoRecebida = usuario.getSolicitacoesRecebidas().stream()
                .filter((Amizade amizade) -> Objects.equals(amizade.getUsuario().getId(), usuarioAutenticadoId))
                .findFirst();

        Optional<Amizade> solicitacaoEnviada = usuario.getSolicitacoesEnviadas().stream()
                .filter((Amizade amizade) -> Objects.equals(amizade.getAmigo().getId(), usuarioAutenticadoId))
                .findFirst();


        if (solicitacaoRecebida.isPresent()) {
            return solicitacaoRecebida.get().getStatus().toString();
        } else if (solicitacaoEnviada.isPresent()) {
            return solicitacaoEnviada.get().getStatus().toString();
        } else {
            return NAO_ADICIONADO_STATUS;
        }
    }
}
