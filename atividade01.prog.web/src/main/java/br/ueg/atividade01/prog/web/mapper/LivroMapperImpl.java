package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.model.Livro;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LivroMapperImpl implements LivroMapper{
    @Override
    public LivroDTO toDTO(Livro livro) {
        return getLivroDTO(livro);
    }



    @Override
    public List<LivroListaDTO> toDTO(List<Livro> livros) {
        List<LivroListaDTO> livroListaDTOs = new ArrayList<>();
        for (Livro l : livros) {
            LivroListaDTO livroListaDTO = new LivroListaDTO();
            livroListaDTO.setIdLivro(l.getIdLivro());
            livroListaDTO.setTitulo(l.getTitulo());
            livroListaDTO.setAutor(l.getAutor());
            livroListaDTO.setAnoPublicacao(l.getAnoPublicacao());
            livroListaDTO.setEditora(l.getEditora());
            livroListaDTOs.add(livroListaDTO);
        }
        return livroListaDTOs;
    }

    @Override
    public LivroAlteravelDTO toLivroIncluirDTO(Livro livro) {
        LivroAlteravelDTO livroAlteravelDTO = new LivroAlteravelDTO();
        livroAlteravelDTO.setTitulo(livro.getTitulo());
        livroAlteravelDTO.setAutor(livro.getAutor());
        livroAlteravelDTO.setAnoPublicacao(livro.getAnoPublicacao());
        livroAlteravelDTO.setEditora(livro.getEditora());
        livroAlteravelDTO.setGenero(livro.getGenero());
        livroAlteravelDTO.setNumeroDePaginas(livro.getNumeroDePaginas());
        return livroAlteravelDTO;
    }

    @Override
    public Livro toModel(LivroAlteravelDTO livro) {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(livro.getTitulo());
        novoLivro.setAutor(livro.getAutor());
        novoLivro.setAnoPublicacao(livro.getAnoPublicacao());
        novoLivro.setEditora(livro.getEditora());
        novoLivro.setGenero(livro.getGenero());
        novoLivro.setNumeroDePaginas(livro.getNumeroDePaginas());
        novoLivro.setDataDeRegistro(LocalDate.now());
        return novoLivro;
    }

    @Override
    public LivroDTO toLivroDTO(Livro livro) {
        return getLivroDTO(livro);
    }

    private LivroDTO getLivroDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setIdLivro(livro.getIdLivro());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        livroDTO.setAnoPublicacao(livro.getAnoPublicacao());
        livroDTO.setEditora(livro.getEditora());
        livroDTO.setGenero(livro.getGenero());
        livroDTO.setNumeroDePaginas(livro.getNumeroDePaginas());
        livroDTO.setDataDeRegistro(livro.getDataDeRegistro());
        return livroDTO;
    }
    /*
    @Override
    public LivroDTO toDTO(Optional<Livro> livro) {
        LivroDTO livroDTO = new LivroDTO();
        if (livro.isPresent()) {
            Livro l = livro.get();
            livroDTO.setIdLivro(l.getIdLivro());
            livroDTO.setTitulo(l.getTitulo());
            livroDTO.setAutor(l.getAutor());
            livroDTO.setAnoPublicacao(l.getAnoPublicacao());
            livroDTO.setEditora(l.getEditora());
            livroDTO.setGenero(l.getGenero());
            livroDTO.setNumeroDePaginas(l.getNumeroDePaginas());
            livroDTO.setDataDeRegistro(l.getDataDeRegistro());
        }
        return livroDTO;
    }*/
}
