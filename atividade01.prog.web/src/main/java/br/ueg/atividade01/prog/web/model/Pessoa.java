package br.ueg.atividade01.prog.web.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "pessoa",
        uniqueConstraints = {
                @UniqueConstraint(name= Pessoa.UK_PESSOA_ID, columnNames = "id_pessoa" )
        }
)
public class Pessoa extends BaseEntidade<Long> {
    public static final String UK_PESSOA_ID = "uk_pessoa_id";
    @Id
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(name = "nome_pessoa", length = 100, nullable = false)
    private String nomePessoa;

    @Column(name = "email_pessoa", length = 150, nullable = false, unique = true)
    private String emailPessoa;
}
