package br.ueg.atividade01.prog.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "idUsuario")
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(name= Usuario.UK_USUARIO, columnNames = "id_usuario" )
        }
)
public class Usuario implements UserDetails {
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

    @Column(name = "usuario_senha", nullable = false)
    private String senhaUsuario;

    @Column(name = "role", nullable = false)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if("admin".equals(this.role)) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senhaUsuario;
    }

    @Override
    public String getUsername() {
        return this.emailUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
