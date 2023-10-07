package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface LivroService {
    public Livro incluir(Livro livro);
    public Livro alterar(Livro livro, long id);
    public Optional<Livro> excluir(long id);
    public Optional<Livro> buscarLivro(long id);
    public List<LivroListaDTO> listarTodosLivros();

}
