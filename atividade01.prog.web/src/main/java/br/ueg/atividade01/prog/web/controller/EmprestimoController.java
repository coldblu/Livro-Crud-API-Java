package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.EmprestimoDTO;
import br.ueg.atividade01.prog.web.dto.EmprestimoListaDTO;
import br.ueg.atividade01.prog.web.mapper.EmprestimoMapper;
import br.ueg.atividade01.prog.web.model.Emprestimo;
import br.ueg.atividade01.prog.web.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${app.api.base}/livro")
@PreAuthorize(value = "isAuthenticated()")
public class EmprestimoController {
    @Autowired
    private EmprestimoMapper emprestimoMapper;
    @Autowired
    private EmprestimoService emprestimoService;
    //Emprestar
    @PostMapping(path = "/emprestar")
    @Operation(description = "Emprestar livro.")
    @ApiResponse(responseCode = "200", description = "Emprestimo realizado com sucesso!",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmprestimoDTO.class)))
    @ApiResponse(responseCode = "404", description = "Erro ao realizar emprestimo.")
    public ResponseEntity<EmprestimoDTO> emprestar(@RequestBody EmprestimoDTO emprestimo) {
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

    @GetMapping("/ativos")
    @Operation(description = "Listar empréstimos ativos")
    @ApiResponse(responseCode = "200", description = "Lista de empréstimos ativos",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema(schema = @Schema(implementation = EmprestimoListaDTO.class))))
    public ResponseEntity<List<EmprestimoListaDTO>> listarEmprestimosAtivos() {
        List<Emprestimo> emprestimosAtivos = emprestimoService.listarEmprestimosAtivos();
        List<EmprestimoListaDTO> emprestimosAtivosDTO = emprestimoMapper.toEmprestimoDTOList(emprestimosAtivos);
        return ResponseEntity.ok(emprestimosAtivosDTO);
    }

    @GetMapping("/ativosDePessoa/{id}")
    @Operation(description = "Listar empréstimos ativos de uma pessoa")
    @ApiResponse(responseCode = "200", description = "Lista de empréstimos ativos",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema(schema = @Schema(implementation = EmprestimoListaDTO.class))))
    public ResponseEntity<List<EmprestimoListaDTO>> listarEmprestimosAtivosDaPessoa(@PathVariable(name = "id") long id ) {
        List<Emprestimo> emprestimosAtivos = emprestimoService.listarEmprestimosAtivosDaPessoa(id);
        List<EmprestimoListaDTO> emprestimosAtivosDTO = emprestimoMapper.toEmprestimoDTOList(emprestimosAtivos);
        return ResponseEntity.ok(emprestimosAtivosDTO);
    }

    @GetMapping("/finalizados")
    @Operation(description = "Listar empréstimos finalizados")
    @ApiResponse(responseCode = "200", description = "Lista de empréstimos ativos",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema(schema = @Schema(implementation = EmprestimoListaDTO.class))))
    public ResponseEntity<List<EmprestimoListaDTO>> listarEmprestimosFinalizados() {
        List<Emprestimo> emprestimosFinalizados = emprestimoService.listarEmprestimosFinalizados();
        List<EmprestimoListaDTO> emprestimosFinalizadosDTO = emprestimoMapper.toEmprestimoDTOList(emprestimosFinalizados);
        return ResponseEntity.ok(emprestimosFinalizadosDTO);
    }

    @GetMapping("/verificarEmprestimoAtivo/{livroId}")
    @Operation(description = "Verificar se o livro possui empréstimo ativo")
    @ApiResponse(responseCode = "200", description = "Verificação concluída")
    public ResponseEntity<Boolean> verificarEmprestimoAtivo(@PathVariable(name = "livroId") long livroId) {
        boolean emprestimoAtivo = emprestimoService.verificarEmprestimoAtivo(livroId);
        return ResponseEntity.ok(emprestimoAtivo);
    }

}
