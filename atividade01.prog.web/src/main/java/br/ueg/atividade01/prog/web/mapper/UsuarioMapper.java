package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO toUsuarioDTO(Usuario usuario);
    Usuario toUsuarioModel(UsuarioDTO usuarioDTO);
}
