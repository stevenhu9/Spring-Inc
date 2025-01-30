package com.Spring_inc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGatewayConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gateway - Aggregated Swagger Docs")
                        .version("1.0")
                        .description("Aggregated API documentation for all microservices"));
    }

    @Bean
    public GroupedOpenApi squadronApi() {
        return GroupedOpenApi.builder()
                .group("squadron-service")
                .pathsToMatch("/squadron-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi pilotApi() {
        return GroupedOpenApi.builder()
                .group("pilot-service")
                .pathsToMatch("/pilot-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commanderApi() {
        return GroupedOpenApi.builder()
                .group("commander-service")
                .pathsToMatch("/commander-service/**")
                .build();
    }
}
