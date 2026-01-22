package com.joaodev.spacex_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI spaceXApi() {
        return new OpenAPI()
           .info(new Info()
           .title("Space data Voyager")
           .description("Aplicação backend em Spring Batch que automatiza o consumo da SpaceX API.")
           .version("v0.0.1")
           .license(new License()
           .name("MIT license")
           .url("https://github.com/Joao-Victor-Teixeira/spacex-data-voyager")));
    }
}