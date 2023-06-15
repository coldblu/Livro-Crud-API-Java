package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapperImpl implements EmprestimoMapper{

    @Override
    public EmprestimoDTO toEmprestimoDTO(Emprestimo emprestimo) {
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setIdEmprestimo(emprestimo.getIdEmprestimo());
        emprestimoDTO.setLivroID(emprestimo.getLivroID());
        emprestimoDTO.setNomePessoa(emprestimo.getNomePessoa());
        emprestimoDTO.setDataEmprestimo(emprestimo.getDataEmprestimo());
        emprestimoDTO.setDataDevolucao(emprestimo.getDataDevolucao());
        emprestimoDTO.setEmprestimoAtivo(emprestimo.isEmprestimoAtivo());

        return emprestimoDTO;
    }
}
