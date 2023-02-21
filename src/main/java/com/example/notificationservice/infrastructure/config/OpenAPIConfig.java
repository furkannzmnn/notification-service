package com.example.notificationservice.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("NOTIFICATION API")
                        .version("1.0")
                        .description("NOTIFICATION")
                        .license(new License().name("NOTIFICATION API LICENCE")));
    }
}
