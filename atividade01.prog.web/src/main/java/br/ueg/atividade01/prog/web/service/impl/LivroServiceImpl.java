package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.repository.LivroRepository;
import br.ueg.atividade01.prog.web.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;
    @Override
    public Livro incluir(Livro livro) {
        return this.inserirDados(livro);
    }

    private Livro inserirDados(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro alterar(Livro livro, long id) {

        return livroRepository.save(livro);
    }

    @Override
    public Optional<Livro> excluir(long id) {
        Optional<Livro> livroBD = livroRepository.findLivroByidLivro(id);
        livroRepository.deleteById(id);
        return livroBD;
    }

    @Override
    public Optional<Livro> buscarLivro(long id) {
        Optional<Livro> livroBD = livroRepository.findLivroByidLivro(id);
        return livroBD;
    }

    @Override
    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }
}
