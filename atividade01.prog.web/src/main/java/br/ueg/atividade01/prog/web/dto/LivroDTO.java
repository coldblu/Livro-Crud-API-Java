package br.ueg.atividade01.prog.web.dto;

import lombok.Data;

import java.time.LocalDate;

public @Data class LivroDTO {
    private long idLivro;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String editora;
    private String genero;
    private int numeroDePaginas;
    private LocalDate dataDeRegistro;
}
