package br.ueg.atividade01.prog.web.repository;

import br.ueg.atividade01.prog.web.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findPessoaByIdPessoa(long id);
    Optional<Pessoa> findPessoaByEmailPessoa(String email);

}
