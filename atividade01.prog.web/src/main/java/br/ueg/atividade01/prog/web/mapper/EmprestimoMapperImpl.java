package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

        return emprestimoDTO;
    }

    @Override
    public Emprestimo toEmprestimoModel(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(emprestimoDTO.getIdEmprestimo());
        emprestimo.setLivroID(emprestimoDTO.getLivroID());
        emprestimo.setNomePessoa(emprestimoDTO.getNomePessoa());
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataDevolucao(emprestimoDTO.getDataDevolucao());
        return emprestimo;
    }

    @Override
    public List<EmprestimoDTO> toEmprestimoDTOList(List<Emprestimo> emprestimos) {
        List<EmprestimoDTO> emprestimosDTO = new ArrayList<>();
        if (!CollectionUtils.isEmpty(emprestimos)) {//Verifica se a lista nao e nula
            for (Emprestimo emprestimo : emprestimos) {
                emprestimosDTO.add(toEmprestimoDTO(emprestimo));
            }
        }
        return emprestimosDTO;
    }


}
