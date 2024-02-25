package com.ingsoftware.munchies.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Munchies API")
                        .description("Munchies API methods and documentation")
                        .version("1.0"));
    }
}