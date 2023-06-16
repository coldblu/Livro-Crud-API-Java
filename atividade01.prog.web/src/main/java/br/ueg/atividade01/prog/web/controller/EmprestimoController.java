package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.dto.LivroListaDTO;
import br.ueg.atividade01.prog.web.mapper.EmprestimoMapper;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${app.api.base}/livro")
public class EmprestimoController {
    @Autowired
    private EmprestimoMapper emprestimoMapper;
    @Autowired
    private EmprestimoService emprestimoService;
    //Emprestimo
    @GetMapping(path = "/emprestar/{id}")
    @Operation(description = "Emprestar livro.")
    @ApiResponse(responseCode = "200", description = "Emprestimo realizado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Erro ao realizar emprestimo.")
    public ResponseEntity<EmprestimoDTO> emprestar(@PathVariable EmprestimoDTO emprestimo) {
        Emprestimo emprestimoIncluir = this.emprestimoMapper.toEmprestimoModel(emprestimo);
        emprestimoIncluir = this.emprestimoService.incluirEmprestimo(emprestimoIncluir);
        EmprestimoDTO emprestimoDTO = this.emprestimoMapper.toEmprestimoDTO(emprestimoIncluir);
        return ResponseEntity.ok(emprestimoDTO);
    }

    @PutMapping(path = "/devolucao/{id}")
    @Operation(description = "Método utilizado para finalizar um emprestimo.")
    @ApiResponse(responseCode = "200", description = "Emprestimo finalizado!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDTO.class)))
    public ResponseEntity<EmprestimoDTO> devolucao(@PathVariable(name = "id") long id ){
        Emprestimo emprestimoBD = emprestimoService.finalizarEmprestimo(id);
        EmprestimoDTO emprestimoDTO = emprestimoMapper.toEmprestimoDTO(emprestimoBD);
        return ResponseEntity.ok(emprestimoDTO);
    }

    @PostMapping(path="/excluirEmprestimo/{id}")
    @Operation(description = "Método utilizado para deletar registro do emprestimo")
    @ApiResponse(responseCode = "200", description = "Emprestimo Excluído",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Emprestimo.class)))
    public ResponseEntity<Optional<Emprestimo>> excluirEmprestimo(@PathVariable(name = "id") long id ){
        Optional<Emprestimo> emprestimoExcluido = this.emprestimoService.excluirEmprestimo(id);
        return ResponseEntity.ok(emprestimoExcluido);
    }
/*
    @GetMapping(path="/listar_emprestimos")
    @Operation(description = "Listagem Geral dos emprestimos ativos.")
    @ApiResponse(responseCode = "200", description = "Listagem de emprestimos.",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema()))
    @ApiResponse(responseCode = "400", description = "Nao encontrado",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<LivroListaDTO>> listarEmprestimos(){
        List<Emprestimo> emprestimosAtivos = emprestimoService.listarEmprestimosAtivos();
        return ResponseEntity.ok(1);
    }*/
}
