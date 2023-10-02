package br.ueg.atividade01.prog.web.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoa",
        uniqueConstraints = {
                @UniqueConstraint(name= Pessoa.UK_PESSOA_ID, columnNames = "id_pessoa" )
        }
)
public class Pessoa {
    public static final String UK_PESSOA_ID = "uk_pessoa_id";
    @Id
    @Column(name = "id_pessoa")
    private long idPessoa;

    @Column(name = "nome_pessoa", length = 100, nullable = false)
    private String nomePessoa;

    @Column(name = "email_pessoa", length = 150, nullable = false, unique = true)
    private String emailPessoa;
}
