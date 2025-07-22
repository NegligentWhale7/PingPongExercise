package com.josealam.pingpongexercise.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI Configuration for PingPong Exercise API
 * Provides API documentation with Jakarta Bean Validation integration
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PingPong Exercise API")
                        .description("API REST para gestión de vehículos, talleres y partes con Jakarta Bean Validation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jose Alam")
                                .email("jose.alam@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
