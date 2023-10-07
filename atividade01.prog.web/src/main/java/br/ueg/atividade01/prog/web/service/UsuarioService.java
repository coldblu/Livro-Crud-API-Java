package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UsuarioService {
    Usuario inserirUsuario(Usuario usuario);
    Usuario alterarUsuario(Usuario usuario, long idUsuario);
    Optional<Usuario> excluirUsuario(long idUsuario);
    Optional<Usuario> buscarUsuario(long idUsuario);
    Optional<Usuario> buscarUsuarioPeloEmail(String email);

}
