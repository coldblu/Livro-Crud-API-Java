package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {
    @Mapping(target = "email", source = "emailUsuario")
    @Mapping(target = "senha", source = "senhaUsuario")
    @Mapping(target = "id", source = "idUsuario")
    @Mapping(target = "")
    public CredencialDTO toCredentialDTO(Usuario usuario);
}
