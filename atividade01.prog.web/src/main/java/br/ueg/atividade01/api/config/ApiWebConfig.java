/*
 * ApiWebConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.atividade01.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de configuração referente aos recursos Web MVC da aplicação.
 *
 * @author UEG
 */
public class ApiWebConfig {

	/**
	 * Retorna a instância de {@link CorsFilter} responsável por desabilitar o
	 * 'CORS' de forma global na aplicação.
	 *
	 * @return
	 */
	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("http://*");
		config.addAllowedHeader("*");
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}*/
	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", this.getCorsConfiguration());
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	public CorsConfiguration getCorsConfiguration() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("http://*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		return config;
	}
}
