package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.mapper.LivroMapper;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.repository.EmprestimoRepository;
import br.ueg.atividade01.prog.web.repository.LivroRepository;
import br.ueg.atividade01.prog.web.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class LivroServiceImpl implements LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroMapper livroMapper;
    @Override
    public Livro incluir(Livro livro) {
        return this.inserirDados(livro);
    }

    private Livro inserirDados(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Livro alterar(Livro livro, long id) {
        // Verificar se o livro com o ID fornecido existe no banco de dados
        Optional<Livro> livroExistenteOptional = livroRepository.findById(id);
        if (livroExistenteOptional.isPresent()) {
            Livro livroExistente = livroExistenteOptional.get();            
            // Atualizar os atributos relevantes do objeto existente com os valores do objeto recebido
            livroExistente.setTitulo(livro.getTitulo());
            livroExistente.setAutor(livro.getAutor());
            livroExistente.setAnoPublicacao(livro.getAnoPublicacao());
            livroExistente.setEditora(livro.getEditora());
            livroExistente.setGenero(livro.getGenero());
            livroExistente.setNumeroDePaginas(livro.getNumeroDePaginas());
                       
            // Salvar o objeto atualizado
            return livroRepository.save(livroExistente);
        } else {
            // Livro com o ID fornecido não encontrado
            throw new NotFoundException("Livro não encontrado");
        }
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
    public List<LivroListaDTO> listarTodosLivros() {
        List<LivroListaDTO> lista = livroMapper.toDTO(livroRepository.findAll());
        lista.forEach(livroListaDTO -> {
            List<Emprestimo> listaEmprestimo = emprestimoRepository.findByLivroAndDataDevolucaoIsNull(livroListaDTO.getIdLivro());
            livroListaDTO.setEmprestado(!listaEmprestimo.isEmpty());

        });
        return lista;
    }


}
