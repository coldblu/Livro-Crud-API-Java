package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.PessoaDTO;
import br.ueg.atividade01.prog.web.dto.PessoaListaDTO;
import br.ueg.atividade01.prog.web.dto.UsuarioDTO;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaDTO> {

    /*@Mapping(source = "idPessoa", target = "idPessoa")
    @Mapping(source = "nomePessoa", target = "nomePessoa")
    @Mapping(source = "emailPessoa", target = "emailPessoa")
    PessoaDTO toPessoaDTO(Optional<Pessoa> pessoa);
    List<PessoaListaDTO> toListaPessoaDTO(List<Pessoa> pessoas);
    Pessoa toPessoaModel(PessoaDTO pessoaDTO);*/
}
