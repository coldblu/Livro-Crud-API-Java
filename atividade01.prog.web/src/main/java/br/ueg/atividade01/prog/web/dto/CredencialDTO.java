package br.ueg.atividade01.prog.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Representação de Credencial do Usuário")
@Builder
public @Data class CredencialDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7616722014159043532L;

    @Schema(description = "Id do Usuário")
    private Long id;

    @Schema(description = "Email do Usário")
    private String email;

    @Schema(description = "Lista de permissões do Usuário")
    private List<String> roles;

    @Schema(description = "Token de acesso")
    private String accessToken;

    @Schema(description = "Tempo de expiração do token de refresh")
    private Long refreshExpiresIn;

    @Schema(description = "Senha do usuário")
    @Hidden
    private String senha;

    @Schema(description = "Tempo de expiração do token de acesso")
    private Long accessTokenExpiresIn;

    @Schema(description = "Token de refresh")
    private String refreshToken;

    @Schema(description = "Tempo de expiração do token de refresh")
    private Long refreshTokenExpiresIn;




    public void setRoles(Collection<? extends GrantedAuthority> authorities) {
        this.roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}