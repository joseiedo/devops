package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.domain.Amizade;
import br.com.cwi.sportivity.repository.AmizadeRepository;
import br.com.cwi.sportivity.service.core.ValidaAmizadeExisteService;
import br.com.cwi.sportivity.service.core.ValidaUsuarioaRemoverFazParteDaAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverSolicitacaoAmizadeService {

    @Autowired
    private ValidaAmizadeExisteService validaAmizadeExisteService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private ValidaUsuarioaRemoverFazParteDaAmizadeService validaUsuarioaRemoverFazParteDaAmizadeService;

    public void remover(Long idAmizade) {
        validaAmizadeExisteService.validar(idAmizade);
        Amizade amizade = amizadeRepository.findById(idAmizade).get();
        validaUsuarioaRemoverFazParteDaAmizadeService.validar(amizade);

        amizadeRepository.delete(amizade);
    }
}
