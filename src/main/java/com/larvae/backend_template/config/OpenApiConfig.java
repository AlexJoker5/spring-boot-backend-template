package com.larvae.backend_template.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures SpringDoc OpenAPI metadata for the application.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates the OpenAPI object used by Swagger UI.
     *
     * @return configured OpenAPI metadata
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Template API")
                        .version("1.0")
                        .description("API documentation for Backend Template"));
    }
}
