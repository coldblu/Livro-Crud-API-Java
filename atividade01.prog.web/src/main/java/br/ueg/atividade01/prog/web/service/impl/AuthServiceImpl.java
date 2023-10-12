package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.dto.AuthDTO;
import br.ueg.atividade01.prog.web.dto.CadastroDTO;
import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.mapper.UsuarioMapper;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.repository.PessoaRepository;
import br.ueg.atividade01.prog.web.repository.UsuarioRepository;
import br.ueg.atividade01.prog.web.service.PessoaService;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class AuthServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PessoaService pessoaService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    PessoaRepository pessoaRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailUsuario(username);
    }

    public CredencialDTO credenciaisDoUsuario(Usuario usuario, String token, Long accessTokenExpiresIn, Long refreshTokenExpiresIn){
        List<String> roles = usuario.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        Pessoa pessoa = pessoaRepository.findPessoaByEmailPessoa(usuario.getEmailUsuario());
        return CredencialDTO.builder()
                .id(usuario.getIdUsuario())
                .pessoaId(pessoa.getIdPessoa())
                .nomePessoa(pessoa.getNomePessoa())
                .email(usuario.getEmailUsuario())
                .roles(roles)
                .accessToken(token)
                .accessTokenExpiresIn(accessTokenExpiresIn)
                .refreshTokenExpiresIn(refreshTokenExpiresIn)
                .build();
    }

    public Usuario cadastrarPessoaUsuario(CadastroDTO cadastroDTO){
        Pessoa pessoa = pessoaService.cadastroPessoa(cadastroDTO);
        if(pessoa != null){
            return usuarioService.cadastroUsuario(cadastroDTO);
        }
        else return null;

    }
}
