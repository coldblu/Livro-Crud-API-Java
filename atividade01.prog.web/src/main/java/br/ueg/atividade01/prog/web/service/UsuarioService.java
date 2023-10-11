package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.dto.AuthDTO;
import br.ueg.atividade01.prog.web.dto.CadastroDTO;
import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.model.Usuario;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface UsuarioService {
    Usuario inserirUsuario(Usuario usuario);
    Usuario alterarUsuario(Usuario usuario, long idUsuario);
    Usuario cadastroUsuario(CadastroDTO cadastroDTO);
    Optional<Usuario> excluirUsuario(long idUsuario);
    Optional<Usuario> buscarUsuario(long idUsuario);
    Boolean validarSenhaUsuario(AuthDTO dados);
    CredencialDTO toCredencialDTO(Authentication auth, String token);
}
