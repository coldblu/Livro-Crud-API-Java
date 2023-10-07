package br.ueg.atividade01.prog.web.mapper;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LivroMapper {
    LivroDTO toDTO(Livro livro);
    @Mapping(target = "emprestado", ignore = true)
    List<LivroListaDTO> toDTO(List<Livro> livros);
    LivroAlteravelDTO toLivroIncluirDTO(Livro livro);
    @Mapping(target = "idLivro", ignore = true)
    @Mapping(target = "dataDeRegistro", ignore = true)
    Livro toModel(LivroAlteravelDTO livro);
    LivroDTO toLivroDTO(Livro livro);

    /*
    @Mapping(source = "idLivro", target = "idLivro")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "autor", target = "autor")
    @Mapping(source = "anoPublicacao", target = "anoPublicacao")
    @Mapping(source = "editora", target = "editora")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "numeroDePaginas", target = "numeroDePaginas")
    @Mapping(source = "dataDeRegistro", target = "dataDeRegistro")
    LivroDTO toDTO(Optional<Livro> livro);*/
}
