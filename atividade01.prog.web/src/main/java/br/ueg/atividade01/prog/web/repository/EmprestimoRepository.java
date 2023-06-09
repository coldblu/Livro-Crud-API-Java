package br.ueg.atividade01.prog.web.repository;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {


    List<Emprestimo> findByLivroIDAndDataDevolucaoIsNull(long id);

    List<Emprestimo> findAllByDataDevolucaoBefore(LocalDate currentDate);

    List<Emprestimo> findAllByDataDevolucaoIsNullOrDataDevolucaoAfter(LocalDate currentDate);
}
