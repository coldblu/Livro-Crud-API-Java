/*
 * AbstractController.java  
 * Copyright UEG.
 *
 */
package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.security.CredentialProvider;
import br.ueg.atividade01.prog.web.service.UserProviderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract Controller.
 * 
 * @author UEG
 */
public abstract class AbstractController {

	@Autowired
	protected UserProviderService userProviderService;

	/**
	 * @return
	 */
	protected CredencialDTO getCredential() {
		return CredentialProvider.newInstance().getCurrentInstance(CredencialDTO.class);
	}

	/**
	 * @return the idUsuarioLogado
	 */
	protected Long getIdUsuarioLogado() {
		CredencialDTO credential = getCredential();
		return credential != null ? credential.getId() : null;
	}

	protected String getLoginLogado() {
		CredencialDTO credential = getCredential();
		return credential != null ? credential.getLogin() : null;
	}


	/**
	 * @return Retorna a instância do {@link CredencialDTO} logado.
	 */
	protected CredencialDTO getUsuarioLogado() {
		CredencialDTO credencialDTO = null;
		String login = getLoginLogado();

		if (login != null) {
			credencialDTO = userProviderService.getCredentialByLogin(login);
		}
		return credencialDTO;
	}

}
