package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class EmprestimoDTO {
    private long idEmprestimo;
    private long livroID;
    private long pessoaID;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
}
