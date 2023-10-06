package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.dto.UsuarioSenhaDTO;

public interface UserProviderService {
    CredencialDTO getCredentialByLogin(String username);
    CredencialDTO redefinirSenha(UsuarioSenhaDTO usuarioSenhaDTO);
    CredencialDTO getCredentialByEmail(String email);
}
