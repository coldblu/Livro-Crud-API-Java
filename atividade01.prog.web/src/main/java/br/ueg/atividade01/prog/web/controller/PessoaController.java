package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.*;
import br.ueg.atividade01.prog.web.mapper.PessoaMapper;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.service.PessoaService;
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
@RequestMapping(path = "${app.api.base}/pessoa")
@PreAuthorize(value = "isAuthenticated()")
public class PessoaController {
    @Autowired
    private PessoaMapper pessoaMapper;
    @Autowired
    private PessoaService pessoaService;

    @PostMapping(path="/incluir_pessoa")
    @Operation(description = "Método utilizado para cadastrar ´pessoa")
    @ApiResponse(responseCode = "200", description = "Pessoa Incluída",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaDTO.class)))
    public ResponseEntity<PessoaDTO> incluirPessoa(@RequestBody PessoaDTO pessoaDTO){
        //prepração para entrada.
        Pessoa novaPessoa = this.pessoaMapper.toPessoaModel(pessoaDTO);

        //chamada do serviço
        System.out.println(novaPessoa);
        novaPessoa = this.pessoaService.incluirPessoa(novaPessoa);

        //preparação para o retorno
        PessoaDTO pessoaDto = this.pessoaMapper.toPessoaDTO(novaPessoa);
        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping(path = "/buscar_pessoa/{id}")
    @Operation(description = "Busca pessoa")
    @ApiResponse(responseCode = "200", description = "Pessoa encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable(name = "id") long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoaPeloId(id);
        if (pessoa.isPresent()) {
            Pessoa pessoaBD = pessoa.get();
            PessoaDTO pessoaDTO = pessoaMapper.toPessoaDTO(pessoaBD);
            return ResponseEntity.ok(pessoaDTO);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
    }

    @GetMapping(path="/listarPessoas")
    @Operation(description = "Lista todo mundo do mundo.")
    @ApiResponse(responseCode = "200", description = "Listagem de pessoa",
            content = @Content(mediaType = "application/json",
                    array=@ArraySchema()))
    @ApiResponse(responseCode = "400", description = "Nao encontrado",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<PessoaListaDTO>> listarTodasPessoas(){
        List<PessoaListaDTO> pessoas = pessoaService.listarTodasPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
