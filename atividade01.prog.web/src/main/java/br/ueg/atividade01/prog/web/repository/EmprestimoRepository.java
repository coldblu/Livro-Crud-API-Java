package br.ueg.atividade01.prog.web.repository;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.model.Pessoa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT e FROM Emprestimo e WHERE e.livro = :livro AND e.dataDevolucao IS NULL")
    List<Emprestimo> findByLivroAndDataDevolucaoIsNull(Livro livro);
    @Query("SELECT e FROM Emprestimo e WHERE e.pessoa = :pessoa AND e.dataDevolucao IS NULL")
    List<Emprestimo> findByPessoaAndDataDevolucaoIsNull(Pessoa pessoa);
    List<Emprestimo> findAllByDataDevolucaoBefore(LocalDate currentDate);

    List<Emprestimo> findAllByDataDevolucaoIsNullOrDataDevolucaoAfter(LocalDate currentDate);
}
