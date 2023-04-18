package br.ueg.atividade01.prog.web.repository;

import br.ueg.atividade01.prog.web.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>  {
    Optional<Livro> findLivroByidLivro(long id);

    Optional<Livro> findLivroByTitulo(String titulo);
}
