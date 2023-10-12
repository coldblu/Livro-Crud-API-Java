package br.ueg.atividade01.prog.web.dto;


import lombok.Data;

public @Data class PessoaListaDTO {
    private long idPessoa;
    private String nomePessoa;
    private String emailPessoa;
}
