package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class EmprestimoListaDTO {
    private long idEmprestimo;
    private long livroID;
    private long pessoaID;
    private String pessoaNome;
    private String livroNome;
    private String pessoaEmail;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
}
