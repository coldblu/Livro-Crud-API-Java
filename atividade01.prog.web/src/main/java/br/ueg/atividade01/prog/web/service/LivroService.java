package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    public Livro incluir(Livro livro);
    public Livro alterar(Livro livro, long id);
    public Optional<Livro> excluir(long id);
    public Optional<Livro> buscarLivro(long id);
    public List<Livro> listarTodosLivros();

    public Emprestimo incluirEmprestimo(Emprestimo emprestimo);
    public List<Emprestimo> listarEmprestimosAtivos();
    public Emprestimo finalizarEmprestimo(long id);
    public Emprestimo excluirEmprestimo(long id);
}
