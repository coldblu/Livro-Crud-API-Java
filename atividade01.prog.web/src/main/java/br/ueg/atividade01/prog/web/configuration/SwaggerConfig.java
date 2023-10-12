/*
 * SwaggerConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.atividade01.prog.web.configuration;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import br.ueg.atividade01.api.config.ApiSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Classe de configuração referente a geração de documentação automatida da API
 * REST.
 * 
 * @author UEG
 */
@Configuration
@Component
public class SwaggerConfig extends ApiSwaggerConfig {
}
