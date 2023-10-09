package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {
}
