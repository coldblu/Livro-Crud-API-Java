package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.PessoaDTO;
import br.ueg.atividade01.prog.web.dto.PessoaListaDTO;
import br.ueg.atividade01.prog.web.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PessoaMapper {
    PessoaDTO toPessoaDTO(Pessoa pessoa);
    List<PessoaListaDTO> toListaPessoaDTO(List<Pessoa> pessoas);
    Pessoa toPessoaModel(PessoaDTO pessoaDTO);
}
