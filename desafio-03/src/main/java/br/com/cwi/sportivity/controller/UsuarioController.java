package br.com.cwi.sportivity.controller;

import br.com.cwi.sportivity.controller.request.EditarUsuarioAutenticadoRequest;
import br.com.cwi.sportivity.controller.request.IncluirUsuarioRequest;
import br.com.cwi.sportivity.controller.response.*;
import br.com.cwi.sportivity.security.service.UsuarioAutenticadoService;
import br.com.cwi.sportivity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private EditarUsuarioAutenticadoService editarUsuarioAutenticadoService;

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private EnviarSolicitacaoAmizadeService enviarSolicitacaoAmizadeService;

    @Autowired
    private AceitarSolicitacaoAmizadeService aceitarSolicitacaoAmizadeService;

    @Autowired
    private RemoverSolicitacaoAmizadeService removerSolicitacaoAmizadeService;

    @Autowired
    private ListarSolicitacoesAmizadeRecebidasService listarSolicitacoesAmizadeRecebidasService;

    @Autowired
    private ListarAmigosService listarAmigosService;

    @Autowired
    private DetalharUsuarioService detalharUsuarioService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public IncluirUsuarioResponse incluir(@Valid @RequestBody IncluirUsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping("/{id}/enviaramizade")
    @ResponseStatus(NO_CONTENT)
    public void enviarSolicitacaoAmizade(@PathVariable Long id){
        enviarSolicitacaoAmizadeService.enviar(id);
    }

    @PutMapping("/aceitaramizade/{id}")
    @ResponseStatus(NO_CONTENT)
    public void aceitarSolicitacaoAmizade(@PathVariable Long id){
        aceitarSolicitacaoAmizadeService.aceitar(id);
    }

    @DeleteMapping("/removeramizade/{id}")
    @ResponseStatus(NO_CONTENT)
    public void removerSolicitacaoAmizade(@PathVariable Long id){
        removerSolicitacaoAmizadeService.remover(id);
    }


    @GetMapping("/solicitacoesrecebidas")
    public Page<AmizadeResumidaResponse> listarSolicitacoesDeAmizadeRecebidas(Pageable pageable){
        return listarSolicitacoesAmizadeRecebidasService.listar(pageable);
    }

    @GetMapping("/amigos")
    public Page<AmizadeResumidaResponse> listarAmigos(@RequestParam(defaultValue = "") String texto, Pageable pageable){
        return listarAmigosService.listar(texto, pageable);
    }

    @GetMapping("/pesquisar")
    public Page<PesquisarUsuarioResponse> pesquisar(@RequestParam(defaultValue = "") String texto, Pageable pageable){
        return listarUsuariosService.pesquisar(texto, pageable);
    }

    @GetMapping("/{id}/detalhes")
    public DetalharUsuarioResponse detalhesUsuario(@PathVariable Long id){

        return detalharUsuarioService.detalhar(id);
    }

    @GetMapping("/me")
    public DetalharUsuarioResponse detalhes(){
        Long usuarioAutenticado = usuarioAutenticadoService.getId();
        return detalharUsuarioService.detalhar(usuarioAutenticado);
    }
    @PutMapping("/editarmeusdados")
    public UsuarioResumidoResponse editar(@Valid @RequestBody EditarUsuarioAutenticadoRequest request){
        return editarUsuarioAutenticadoService.editar(request);
    }

}
