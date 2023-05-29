package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.mapper.LivroMapper;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @ApiResponse(responseCode = "200", description = "Listagem de livros",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema()))
    @ApiResponse(responseCode = "400", description = "Nao encontrado",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<LivroListaDTO>> listAll(){
        List<Livro> livros = livroService.listarTodosLivros();
        return ResponseEntity.ok(livroMapper.toDTO(livros));
    }

    @PostMapping(path="/incluir")
    @Operation(description = "Método utilizado para realizar a inclusão de um livro")
    @ApiResponse(responseCode = "200", description = "Livro Incluído",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDTO.class)))
    public ResponseEntity<LivroDTO> incluir(@RequestBody LivroAlteravelDTO livro){
        //prepração para entrada.
        Livro livroIncluir = this.livroMapper.toModel(livro);

        //chamada do serviço
        System.out.println(livroIncluir);
        livroIncluir = this.livroService.incluir(livroIncluir);

        //preparação para o retorno
        LivroDTO livroDTO = this.livroMapper.toLivroDTO(livroIncluir);
        return ResponseEntity.ok(livroDTO);
    }

    @PostMapping(path="/deletar/{id}")
    @Operation(description = "Método utilizado para deletar livro do banco de dados")
    @ApiResponse(responseCode = "200", description = "Livro Excluído",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Livro.class)))
    public ResponseEntity<Optional<Livro>> excluir(@RequestBody LivroDTO livro, @PathVariable(name = "id") Long idLivro ){
        Optional<Livro> livroExcluido = this.livroService.excluir(idLivro);
        return ResponseEntity.ok(livroExcluido);
    }

    @PutMapping(path = "/alterar")
    @Operation(description = "Método utilizado para altlerar os dados de um livro")
    @ApiResponse(responseCode = "200", description = "Livro Alterado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDTO.class)))
    public ResponseEntity<LivroDTO> alterar(@RequestBody() LivroAlteravelDTO livro, @PathVariable(name = "id") Long idLivro ){
        Livro paraLivro = livroMapper.toModel(livro);
        Livro alterar = livroService.alterar(paraLivro, idLivro);
        LivroDTO livroDTO = livroMapper.toLivroDTO(alterar);
        return ResponseEntity.ok(livroDTO);
    }

    @GetMapping(path = "/buscar/{id}")
    @Operation(description = "Buscar um livro pelo título")
    @ApiResponse(responseCode = "200", description = "Livro encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivroDTO.class)))
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    public ResponseEntity<LivroDTO> buscar(@PathVariable(name = "id") long id) {
        Optional<Livro> livro = livroService.buscarLivro(id);
        if (livro.isPresent()) {
            LivroDTO livroDTO = livroMapper.toDTO(livro);
            return ResponseEntity.ok(livroDTO);
        } else {
            throw new IllegalArgumentException("Livro não encontrado");
        }
    }
}