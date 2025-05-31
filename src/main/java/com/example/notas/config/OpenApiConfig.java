package com.example.notas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; 


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("API - proyecto integrador - notas")  // Custom title                        
                .description("Cesde - notas"))
                .addServersItem(new Server()
                .url("http://localhost:8080")
                .description("Servidor Local"));                
    }
}
