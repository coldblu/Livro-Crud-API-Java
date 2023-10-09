package br.ueg.atividade01.prog.web.security;

import br.ueg.atividade01.api.config.ApiWebConfig;
import br.ueg.atividade01.prog.web.configuration.LogoutService;
import br.ueg.atividade01.prog.web.exception.FilterChainExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Value("${app.api.security.url-auth-controller:/api/v1/auth}")
    private String urlAuthController;
    @Autowired
    SecurityFilter securityFilter;
    @Autowired
    private ApiWebConfig apiWebConfig;
    @Autowired
    protected LogoutService logoutHandler;
    @Autowired
    private FilterChainExceptionHandler filterChainExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       /* return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST,"/livro").permitAll()
                        // Adicionar as configurações do Swagger aqui
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();*/
        List<String> freeAccessPatternsList = new ArrayList<>(
                Arrays.asList(urlAuthController.concat("/**"),
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html",
                "/api/v1/auth/login",
                "/api/v1/auth/cadastro"));
        String[] freeAccessPatterns = freeAccessPatternsList.toArray(new String[0]);

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        freeAccessPatterns
                ).permitAll()
                .anyRequest().anonymous()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filterChainExceptionHandler, LogoutFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .and()
                .cors().configurationSource(request -> {
                    return apiWebConfig.getCorsConfiguration();
                });

        return httpSecurity.build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //Fazer Criptografia das senhas
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected List<String> getCustomFreeAccessPaterns() {
        return Arrays.asList();
    };
}
