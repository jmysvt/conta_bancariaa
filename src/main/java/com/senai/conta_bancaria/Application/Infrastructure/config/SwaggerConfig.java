package com.senai.conta_bancaria.Application.Infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Conta Bancária")
                        .description("API para cadastro, autenticação e gestão de contas bancárias e clientes.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Equipe Conta Bancária")
                                .email("suporte@contabancaria.com"))
                );
    }

}
