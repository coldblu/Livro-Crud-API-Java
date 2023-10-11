package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Usuario;
import org.mapstruct.Mapper;
import org.springframework.security.core.Authentication;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toUsuarioDTO(Usuario usuario);
    Usuario toUsuarioModel(UsuarioDTO usuarioDTO);
    CredencialDTO toCredencialDTO(Authentication auth, String token);
}
