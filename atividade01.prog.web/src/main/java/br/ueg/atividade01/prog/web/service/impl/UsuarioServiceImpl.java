package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.dto.AuthDTO;
import br.ueg.atividade01.prog.web.dto.CadastroDTO;
import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.mapper.UsuarioMapper;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.repository.UsuarioRepository;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Usuario inserirUsuario(Usuario usuario) {
        String senhaPlaintext = usuario.getSenhaUsuario(); // Obtém a senha não criptografada
        String senhaCriptografada = passwordEncoder.encode(senhaPlaintext); // Criptografa a senha

        // Define a senha criptografada no objeto Usuario
        usuario.setSenhaUsuario(senhaCriptografada);

        // Salva o usuário no banco de dados
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
    public Usuario cadastroUsuario(CadastroDTO cadastroDTO) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmailUsuario(cadastroDTO.getLogin());
        novoUsuario.setSenhaUsuario(cadastroDTO.getSenha());
        novoUsuario.setRole("comum");
        return this.inserirUsuario(novoUsuario);
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
    public Boolean validarSenhaUsuario(AuthDTO dados) {
        Usuario usuario = usuarioRepository.findUsuarioByEmailUsuario(dados.getLogin());
        return passwordEncoder.matches(dados.getSenha(), usuario.getSenhaUsuario());
    }

    @Override
    public CredencialDTO toCredencialDTO(Authentication auth, String token) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalArgumentException("Authentication não é válida ou autenticada.");
        }
        Usuario usuario = (Usuario) auth.getPrincipal();

        CredencialDTO credencialDTO = new CredencialDTO();
        credencialDTO.setId(usuario.getIdUsuario());
        credencialDTO.setEmail(usuario.getEmailUsuario());
        credencialDTO.setRoles(usuario.getAuthorities());
        credencialDTO.setAccessToken(token);
        return credencialDTO;
    }
}
