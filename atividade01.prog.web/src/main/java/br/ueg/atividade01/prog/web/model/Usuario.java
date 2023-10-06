package br.ueg.atividade01.prog.web.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Getter
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(name= Usuario.UK_USUARIO, columnNames = "id_usuario" )
        }
)
public class Usuario {
    public static final String UK_USUARIO = "uk_usuario";
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
    @Column(name = "id_usuario")
    private long idUsuario;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String emailUsuario;

    @Column(name = "usuario_senha")
    private String senhaUsuario;

}
