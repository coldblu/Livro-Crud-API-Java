package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.mapper.UsuarioMapper;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import br.ueg.prog.webi.api.dto.UsuarioSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserProviderService implements br.ueg.prog.webi.api.service.UserProviderService {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Override
    public CredencialDTO getCredentialByEmail(String email) {
        Optional<Usuario> usuarioOptional = this.usuarioService.buscarUsuarioPeloEmail(email);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String senhaCodificada = bCryptPasswordEncoder.encode(usuario.getSenhaUsuario());
            CredencialDTO credencialDTO = this.usuarioMapper.toCredentialDTO(usuario);
            credencialDTO.setRoles(Arrays.asList(usuario.getRole()));
            credencialDTO.setSenha(senhaCodificada);
            return credencialDTO;
        }
        else{
            return null;
        }

    }



    @Override
    public CredencialDTO getCredentialByLogin(String username) {
        return this.getCredentialByEmail(username);
    }

    @Override
    public CredencialDTO redefinirSenha(UsuarioSenhaDTO usuarioSenhaDTO) {
        return null;
        //return usuarioMapper.toCredentialDTO(this.usuarioService.redefinirSenha(usuarioSenhaDTO));
    }
}