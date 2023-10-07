package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface EmprestimoService {
    public Emprestimo incluirEmprestimo(Emprestimo emprestimo);
    public List<Emprestimo> listarEmprestimosAtivos();
    public Emprestimo finalizarEmprestimo(long id);
    public Optional<Emprestimo> excluirEmprestimo(long id);
    public Optional<Emprestimo> buscarEmprestimo(long id);
    public boolean verificarEmprestimoAtivo(long id);
    public List<Emprestimo> listarEmprestimosFinalizados();
}
