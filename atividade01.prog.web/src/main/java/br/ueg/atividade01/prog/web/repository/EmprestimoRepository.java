package br.ueg.atividade01.prog.web.repository;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByEmprestimoAtivo(boolean b);
}
