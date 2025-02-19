package com.example.toko.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")   // Semua endpoint
        .allowedOrigins("http://localhost:3000")   // Asal yang diizinkan
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Metode HTTP yang diizinkan
        .allowedHeaders("*")   // Mengizinkan semua header
        .allowCredentials(true);  
    }
}