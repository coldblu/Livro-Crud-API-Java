package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class EmprestimoDTO {
    private long idEmprestimo;
    private long livroID;
    private String nomePessoa;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean emprestimoAtivo;
}
