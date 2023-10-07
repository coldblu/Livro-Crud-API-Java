package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.mapper.UsuarioMapper;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.repository.UsuarioRepository;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;
@Service
@Component
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Override
    public Usuario inserirUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario alterarUsuario(Usuario usuario, long idUsuario) {
        Optional<Usuario> usuarioBD = usuarioRepository.findUsuarioByIdUsuario(idUsuario);
        if(usuarioBD.isPresent()){
            Usuario usuarioAlterar = usuarioBD.get();
            usuarioAlterar.setEmailUsuario(usuario.getEmailUsuario());
            usuarioAlterar.setSenhaUsuario(usuario.getSenhaUsuario());
            return usuarioRepository.save(usuarioAlterar);
        } else {
            throw new NotFoundException("Pessoa não encontrado");
        }
    }

    @Override
    public Optional<Usuario> excluirUsuario(long idUsuario) {
        Optional<Usuario> usuarioBD = usuarioRepository.findUsuarioByIdUsuario(idUsuario);
        usuarioRepository.deleteById(idUsuario);
        return usuarioBD;
    }

    @Override
    public Optional<Usuario> buscarUsuario(long idUsuario) {
        Optional<Usuario> usuarioBD = usuarioRepository.findUsuarioByIdUsuario(idUsuario);
        return usuarioBD;
    }

    @Override
    public Optional<Usuario> buscarUsuarioPeloEmail(String email) {
        return Optional.empty();
    }
}
