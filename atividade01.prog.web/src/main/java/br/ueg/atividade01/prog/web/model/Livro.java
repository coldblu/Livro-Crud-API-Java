package br.ueg.atividade01.prog.web.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "livro",
        uniqueConstraints = {
                @UniqueConstraint(name= Livro.UK_AMIGO_MAIL, columnNames = "id_livro" )
        }
)
public class Livro {
    public static final String UK_AMIGO_MAIL = "uk_amigo_mail";
    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "amigo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )

    @Id
    @Column(name = "id_livro")
    private long idLivro;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "autor", length = 50, nullable = false)
    private String autor;

    @Column(name = "ano_publi", nullable = false)
    private int anoPublicacao;

    @Column(name = "editora", length = 50, nullable = false)
    private String editora;

    @Column(name = "genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "numero_paginas", nullable = false)
    private int numeroDePaginas;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataDeRegistro;

}
