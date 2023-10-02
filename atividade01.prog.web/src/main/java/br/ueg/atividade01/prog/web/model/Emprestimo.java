package br.ueg.atividade01.prog.web.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "emprestimo",
        uniqueConstraints = {
                @UniqueConstraint(name= Emprestimo.UK_EMPRESTIMO, columnNames = "id_emprestimo" )
        }
)
public class Emprestimo {
    public static final String UK_EMPRESTIMO = "uk_emprestimo";
    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "emprestimo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )
    @Id
    @Column(name = "id_emprestimo")
    private long idEmprestimo;
    //Many to one
    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id_livro")
    private long livroID;
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id_pessoa")
    private long pessoaID;


    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;
    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;
}
