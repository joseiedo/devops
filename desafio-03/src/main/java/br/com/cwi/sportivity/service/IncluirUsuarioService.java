package br.com.cwi.sportivity.service;

import br.com.cwi.sportivity.controller.request.IncluirUsuarioRequest;
import br.com.cwi.sportivity.controller.response.IncluirUsuarioResponse;
import br.com.cwi.sportivity.domain.Usuario;
import br.com.cwi.sportivity.repository.UsuarioRepository;
import br.com.cwi.sportivity.security.domain.Permissao;
import br.com.cwi.sportivity.service.core.ValidaEmailUnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.sportivity.mapper.IncluirUsuarioMapper.toEntity;
import static br.com.cwi.sportivity.mapper.IncluirUsuarioMapper.toResponse;
import static br.com.cwi.sportivity.security.domain.Funcao.USUARIO;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Transactional
    public IncluirUsuarioResponse incluir(IncluirUsuarioRequest request) {
        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.adicionarPermissao(getPermissaoPadrao());
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

    private Permissao getPermissaoPadrao() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }
}
