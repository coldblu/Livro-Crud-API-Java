package br.ueg.atividade01.prog.web.repository;

import br.ueg.atividade01.prog.web.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByIdUsuario(long id);
    UserDetails findByEmailUsuario(String email);
    Usuario findUsuarioByEmailUsuario(String email);
}
