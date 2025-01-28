package com.Spring_inc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi squadronServiceApi() {
        return GroupedOpenApi.builder()
            .group("Squadron Service")
            .pathsToMatch("/squadron-service/**")
            .build();
    }

    @Bean
    public GroupedOpenApi pilotServiceApi() {
        return GroupedOpenApi.builder()
            .group("Pilot Service")
            .pathsToMatch("/pilot-service/**")
            .build();
    }

    @Bean
    public GroupedOpenApi commanderServiceApi() {
        return GroupedOpenApi.builder()
            .group("Commander Service")
            .pathsToMatch("/commander-service/**")
            .build();
    }
}