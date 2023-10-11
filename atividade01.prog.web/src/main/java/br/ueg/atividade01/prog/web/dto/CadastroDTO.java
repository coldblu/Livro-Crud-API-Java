package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

public @Data class CadastroDTO {
    private String nomePessoa;
    private String senha;
    private String emailPessoa;
}
