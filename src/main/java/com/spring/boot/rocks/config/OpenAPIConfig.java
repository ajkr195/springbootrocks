package com.spring.boot.rocks.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SPRING BOOT ROCKS")
                        .description("SPRING BOOT ROCKS Description")
                        .version("The Version")
                        .contact(new Contact()
                                .name("SPRING BOOT ROCKS")
                                .url("https://github.com/ajkr195/?tab=repositories")
                                .email("https://github.com/ajkr195/?tab=repositories"))
                        .termsOfService("https://github.com/ajkr195/?tab=repositories")
                        .license(new License()
                                .name("SPRING BOOT ROCKS License")
                                .url("https://github.com/ajkr195/?tab=repositories")))
                .externalDocs(new ExternalDocumentation()
                        .description("SPRING BOOT ROCKS Documentation")
                        .url("https://github.com/ajkr195/?tab=repositories"));
    }
}
