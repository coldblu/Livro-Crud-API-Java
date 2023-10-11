package br.ueg.atividade01.prog.web.service.impl;

import br.ueg.atividade01.prog.web.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenServiceImpl {
    @Value("${api.security.token.secret}")
    private String segredo;

    private Set<String> blacklistedAccessTokens = new HashSet<>();
    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            String token = JWT.create()
                    .withIssuer("LivroApi")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(this.expirarEm())
                    .sign(algorithm);
                    return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token.",exception);
        }
    }

    public String gerarRefreshToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            String refreshToken = JWT.create()
                    .withIssuer("LivroApi")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(this.expirarEm()) // Use o mesmo tempo de expiração do token de acesso
                    .sign(algorithm);
            return refreshToken;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar refresh token.", exception);
        }
    }



    // ... Outros métodos ...

    public void revokeAccessToken(String accessToken) {
        // Adicione o token de acesso à lista negra
        blacklistedAccessTokens.add(accessToken);
    }

    // Verifique se um token de acesso está na lista negra (foi revogado)
    public boolean isAccessTokenRevoked(String accessToken) {
        return blacklistedAccessTokens.contains(accessToken);
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.require(algorithm)
                    .withIssuer("LivroApi")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (IllegalArgumentException e) {
            return "";
        }
    }

    /*Gera tempo de expiração para token
    private Instant expirarEm() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.ofHours(-3));
    }*/
    private Instant expirarEm() {
        Duration duration = Duration.ofHours(3);
        return Instant.now().plus(duration);
    }

    public Long getAccessTokenExpirationMillis() {
        return this.expirarEm().toEpochMilli();
    }

    public Long getRefreshTokenExpirationMillis() {
        return this.expirarEm().toEpochMilli();
    }

}
