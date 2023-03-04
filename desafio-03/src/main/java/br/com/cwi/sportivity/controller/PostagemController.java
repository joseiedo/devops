package br.com.cwi.sportivity.controller;

import br.com.cwi.sportivity.controller.request.ComentarPostagemRequest;
import br.com.cwi.sportivity.controller.request.EditarPostagemRequest;
import br.com.cwi.sportivity.controller.request.IncluirPostagemRequest;
import br.com.cwi.sportivity.controller.response.ComentarioResponse;
import br.com.cwi.sportivity.controller.response.PostagemResumidaResponse;
import br.com.cwi.sportivity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private IncluirPostagemService incluirPostagemService;

    @Autowired
    private RemoverPostagemService removerPostagemService;

    @Autowired
    private EditarPostagemService editarPostagemService;

    @Autowired
    private ListarPostagemService listarPostagemService;

    @Autowired
    private CurtirPostagemService curtirPostagemService;

    @Autowired
    private DescurtirPostagemService descurtirPostagemService;

    @Autowired
    private ComentarPostagemService comentarPostagemService;

    @Autowired
    private ListarComentariosPostagemService listarComentariosPostagemService;

    @PostMapping
    @ResponseStatus(value = CREATED)
    public PostagemResumidaResponse incluir(@Valid @RequestBody IncluirPostagemRequest request){
        return incluirPostagemService.incluir(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void remover(@PathVariable Long id){
        removerPostagemService.remover(id);
    }

    @PutMapping("/{id}")
    public PostagemResumidaResponse editar(@PathVariable Long id, @Valid @RequestBody EditarPostagemRequest request){
        return editarPostagemService.editar(id, request);
    }

    @PutMapping("/{id}/curtir")
    public PostagemResumidaResponse curtir(@PathVariable Long id){
        return curtirPostagemService.curtir(id);
    }

    @PutMapping("/{id}/descurtir")
    public PostagemResumidaResponse descurtir(@PathVariable Long id){
        return descurtirPostagemService.descurtir(id);
    }

    @PostMapping("/{id}/comentar")
    public ComentarioResponse comentar(@PathVariable Long id, @Valid @RequestBody ComentarPostagemRequest request){
        return comentarPostagemService.comentar(id, request);
    }

    @GetMapping("/{id}/comentarios")
    public List<ComentarioResponse> listarComentarios(@PathVariable Long id){
        return listarComentariosPostagemService.listar(id);
    }

    @GetMapping("/listar")
    public Page<PostagemResumidaResponse> listar(Pageable pageable){
        return listarPostagemService.listar(pageable);
    }



}
