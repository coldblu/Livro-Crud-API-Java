package br.ueg.atividade01.prog.web.controller;

import br.ueg.atividade01.prog.web.dto.AuthDTO;
import br.ueg.atividade01.prog.web.dto.CadastroDTO;
import br.ueg.atividade01.prog.web.dto.CredencialDTO;
import br.ueg.atividade01.prog.web.mapper.UsuarioMapper;
import br.ueg.atividade01.prog.web.model.Usuario;
import br.ueg.atividade01.prog.web.repository.UsuarioRepository;
import br.ueg.atividade01.prog.web.service.UsuarioService;
import br.ueg.atividade01.prog.web.service.impl.AuthServiceImpl;
import br.ueg.atividade01.prog.web.service.impl.TokenServiceImpl;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;

@RestController
@RequestMapping("${app.api.base}/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenServiceImpl tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthServiceImpl authService;


    @Operation(description = "Logar no sistema",responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
            @ApiResponse(responseCode = "403", description = "Proibido",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
    })
    @PostMapping(path = "/login")
    public ResponseEntity<CredencialDTO> login(@RequestBody @Valid AuthDTO dados) {
        if(usuarioService.validarSenhaUsuario(dados)){
            var usernamePassword = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.gerarToken((Usuario)auth.getPrincipal());
            // Verifique se o token é vazio
            String teste = tokenService.validarToken(token);
            System.out.println(teste);
            if (teste.isEmpty()) {
                return null;
            }
            // Obtenha o tempo de expiração do token e do token de refresh do serviço TokenServiceImpl
            Long accessTokenExpiresIn = tokenService.getAccessTokenExpirationMillis();
            Long refreshTokenExpiresIn = tokenService.getRefreshTokenExpirationMillis();

            //CredencialDTO com as informações necessárias

            Usuario usuario = (Usuario) auth.getPrincipal();
            CredencialDTO credencialDTO = authService.credenciaisDoUsuario(usuario,token,accessTokenExpiresIn,refreshTokenExpiresIn);

            return ResponseEntity.ok(credencialDTO);
        } else {
            return ResponseEntity.badRequest().build();
       }

    }


    @Operation(description = "Logout do sistema")
    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request) {
        // Obtenha o token de acesso do cabeçalho de autorização
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extraia o token de acesso do cabeçalho
            String token = authorizationHeader.substring(7); // "Bearer " possui 7 caracteres

            // Adicione o token de acesso à lista negra (revogue o token)
            tokenService.revokeAccessToken(token);

            return ResponseEntity.ok(new MessageResponse());
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse());
        }
    }


    @Operation(description = "Cadastrar no sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CadastroDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = MessageResponse.class))))
            })
    @PostMapping(path = "/cadastro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> cadastro(@RequestBody @Valid CadastroDTO dados) {
        if (usuarioRepository.findByEmailUsuario(dados.getEmailPessoa()) != null)
            return ResponseEntity.badRequest().build();
        else {
            Usuario usuario = authService.cadastrarPessoaUsuario(dados);
            return ResponseEntity.ok(usuario);
        }
    }
}
