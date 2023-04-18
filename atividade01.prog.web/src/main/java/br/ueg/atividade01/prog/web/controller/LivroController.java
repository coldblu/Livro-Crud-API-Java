package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.mapper.LivroMapper;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${app.api.base}/livro")
public class LivroController {

    @Autowired
    private LivroMapper livroMapper;

    @Autowired
    private LivroService livroService;

    @GetMapping(path="/listar")
    @Operation(description = "Listagem Geral dos livros")
    public List<LivroListaDTO> listAll(){
        List<Livro> livros = livroService.listarTodosLivros();
        return livroMapper.toDTO(livros);
    }

    @PostMapping(path="/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de um livro")
    public LivroDTO incluir(@RequestBody LivroAlteravelDTO livro){
        //prepração para entrada.
        Livro livroIncluir = this.livroMapper.toModel(livro);

        //chamada do serviço
        System.out.println(livroIncluir);
        livroIncluir = this.livroService.incluir(livroIncluir);

        //preparação para o retorno
        return this.livroMapper.toLivroDTO(livroIncluir);
    }

    @PostMapping(path="/deletar")
    @Operation(description = "Método utilizado para deletar livro do banco de dados")
    public Optional<Livro> excluir(@RequestBody LivroDTO livro, @PathVariable(name = "id") Long idLivro ){
        Optional<Livro> livroExcluido = this.livroService.excluir(idLivro);
        return livroExcluido;
    }

    @PutMapping(path = "/alterar")
    @Operation(description = "Método utilizado para altlerar os dados de um livro")
    public LivroDTO alterar(@RequestBody() LivroAlteravelDTO livro, @PathVariable(name = "id") Long idLivro ){
        Livro paraLivro = livroMapper.toModel(livro);
        Livro alterar = livroService.alterar(paraLivro, idLivro);
        return livroMapper.toLivroDTO(alterar);
    }

    @GetMapping(path="/buscar")
    @Operation(description = "Listagem Geral dos livros")
    public LivroDTO buscar(@RequestBody LivroDTO l,@PathVariable(name = "titulo") String titulo ){
        List<String> erros = new ArrayList<>();
        Optional<Livro> livro = livroService.buscarLivro(titulo);
        if(livro.isPresent()){
            return livroMapper.toDTO(livro);
        }
        else{
            throw  new IllegalArgumentException("Livro nao encontrado "+
                    String.join(",", erros)
            );
        }

    }

}
