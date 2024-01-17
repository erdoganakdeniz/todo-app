package com.techer.todoapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


@Configuration
public class SwaggerConfig {

    private static final String AUTH="Authorization";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .components(new Components()
                        .addSecuritySchemes(AUTH, new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER).name(AUTH)
                        ))
                .info(new Info()
                        .title("Todo App Service")
                        .description("Todo App Service Restfull API").version("v0.0.1"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(AUTH, Collections.singletonList("all"))
                );
    }
}
