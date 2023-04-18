package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

public @Data class LivroListaDTO {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String editora;
}
