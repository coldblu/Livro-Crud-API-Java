package br.ueg.atividade01.prog.web.service;

import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroService {
    public Livro incluir(Livro livro);
    public Livro alterar(Livro livro, long id);
    public Livro excluir(long id);
    public Livro buscarLivro(long id);
    public Optional<Livro> buscarLivroPeloId(long id);
    public List<LivroListaDTO> listarTodosLivros();

}
