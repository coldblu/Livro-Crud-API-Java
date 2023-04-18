package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

public @Data class LivroAlteravelDTO {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String editora;
    private String genero;
    private int numeroDePaginas;
}
