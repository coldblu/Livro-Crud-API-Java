package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

//@Mapper(componentModel = "spring")
public interface LivroMapper {
    LivroDTO toDTO(Livro livro);

    List<LivroListaDTO> toDTO(List<Livro> livros);
    LivroAlteravelDTO toLivroIncluirDTO(Livro livro);
    Livro toModel(LivroAlteravelDTO livro);
    LivroDTO toLivroDTO(Livro livro);

    LivroDTO toDTO(Optional<Livro> livro);
}
