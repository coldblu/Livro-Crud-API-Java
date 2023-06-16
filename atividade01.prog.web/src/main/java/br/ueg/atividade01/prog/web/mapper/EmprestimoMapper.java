package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {
    EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo);
    Emprestimo toEmprestimoModel(EmprestimoDTO emprestimoDTO);
    List<EmprestimoDTO> toListDTO();
}
