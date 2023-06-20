package br.ueg.atividade01.prog.web.dto;

import java.time.LocalDate;

public class EmprestimoListaDTO {
    private long idEmprestimo;
    private long livroID;
    private String nomePessoa;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
}
