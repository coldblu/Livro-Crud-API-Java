package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.LivroAlteravelDTO;
import br.ueg.atividade01.prog.web.dto.LivroDTO;
import br.ueg.atividade01.prog.web.dto.PessoaDTO;
import br.ueg.atividade01.prog.web.mapper.PessoaMapper;
import br.ueg.atividade01.prog.web.model.Livro;
import br.ueg.atividade01.prog.web.model.Pessoa;
import br.ueg.atividade01.prog.web.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "${app.api.base}/pessoa")
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
        Pessoa novaPessoa = this.pessoaMapper.toModelo(pessoaDTO);

        //chamada do serviço
        System.out.println(novaPessoa);
        novaPessoa = this.pessoaService.incluirPessoa(novaPessoa);

        //preparação para o retorno
        PessoaDTO pessoaDto = this.pessoaMapper.toDTO(novaPessoa);
        return ResponseEntity.ok(pessoaDto);
    }

    @GetMapping(path = "/buscar_pessoa/{id}")
    @Operation(description = "Busca pessoa")
    @ApiResponse(responseCode = "200", description = "Pessoa encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PessoaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable(name = "id") long id) {
        Optional<Pessoa> pessoaOptional = pessoaService.buscarPessoaPeloId(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            PessoaDTO pessoaDTO = pessoaMapper.toDTO(pessoa);
            return ResponseEntity.ok(pessoaDTO);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
    }
}
