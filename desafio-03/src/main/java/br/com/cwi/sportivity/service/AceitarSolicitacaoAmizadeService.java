package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.service.core.NowService;
import br.com.cwi.sportivity.service.core.ValidaAmizadeExisteService;
import br.com.cwi.sportivity.service.core.ValidaStatusAmizadePendenteService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioAceitandoSolicitacaoIgualUsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.domain.StatusAmizade.ACEITO;

@Service
public class AceitarSolicitacaoAmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private ValidaAmizadeExisteService validaAmizadeExisteService;

    @Autowired
    private ValidaStatusAmizadePendenteService validaStatusAmizadePendenteService;

    @Autowired
    private ValidaUsuarioAceitandoSolicitacaoIgualUsuarioAutenticadoService validaUsuarioAceitandoSolicitacaoIgualUsuarioAutenticadoService;

    @Autowired
    private NowService nowService;

    @Transactional
    public void aceitar(Long idAmizade) {
        validaAmizadeExisteService.validar(idAmizade);
        Amizade amizade = amizadeRepository.findById(idAmizade).get();
        validaStatusAmizadePendenteService.validar(amizade.getStatus());
        validaUsuarioAceitandoSolicitacaoIgualUsuarioAutenticadoService.validar(amizade);

        amizade.setStatus(ACEITO);
        amizade.setDataAmizade(nowService.getDateTime());

        amizadeRepository.save(amizade);
    }
}
