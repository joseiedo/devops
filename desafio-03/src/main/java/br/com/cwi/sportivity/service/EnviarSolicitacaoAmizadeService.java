package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.core.NowService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioAAdicionarDiferenteDoAutenticadoService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioExisteService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioNaoTemAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.domain.StatusAmizade.PENDENTE;

@Service
public class EnviarSolicitacaoAmizadeService {

    @Autowired
    private ValidaUsuarioExisteService validaUsuarioExisteService;

    @Autowired
    private ValidaUsuarioNaoTemAmizadeService validaUsuarioNaoTemAmizadeService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidaUsuarioAAdicionarDiferenteDoAutenticadoService validaUsuarioAAdicionarDiferenteDoAutenticadoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NowService nowService;

    @Transactional
    public void enviar(Long idUsuarioAAdicionar) {
        validaUsuarioExisteService.validar(idUsuarioAAdicionar);

        Usuario usuarioAAdicionar = usuarioRepository.findById(idUsuarioAAdicionar).get();
        validaUsuarioNaoTemAmizadeService.validar(usuarioAAdicionar);

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        validaUsuarioAAdicionarDiferenteDoAutenticadoService.validar(usuarioAAdicionar, usuarioAutenticado);

        Amizade amizade = new Amizade();

        amizade.adicionarNovaSolicitacao(usuarioAAdicionar, usuarioAutenticado);
        amizade.setDataAmizade(nowService.getDateTime());
        amizade.setStatus(PENDENTE);

        amizadeRepository.save(amizade);
    }
}
