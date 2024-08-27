package com.ifba.email.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	 public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:5173","http://192.168.100.76:8082") // Altere para o domínio que você deseja permitir
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowCredentials(true)
	                .allowedHeaders("*");
	 }
}