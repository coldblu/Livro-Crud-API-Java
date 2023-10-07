package br.ueg.atividade01.prog.web.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@Table(name = "usuario")
public class Usuario extends BaseEntidade<Long> {
    public static final class Coluna {
        public static final String ID = "userid";
    }
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
    @Column(name = Coluna.ID)
    private Long idUsuario;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String emailUsuario;

    @Column(name = "usuario_senha")
    private String senhaUsuario;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "role", length = 200, nullable = true)
    private String role;

    @Override
    public String getTabelaNome() {
        return "usuario";
    }

    @Override
    public Long getId() {
        return idUsuario;
    }

    @Override
    public void setId(Long id) {
        this.idUsuario = id;
    }

}
