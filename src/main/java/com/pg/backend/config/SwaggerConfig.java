package com.pg.backend.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiSwaggerConfig(){
        return new OpenAPI()
                .info(new Info().title("PG Api's")
                        .description("All the api's for dhandha!!")
                        .version("/V1")
                        .license(new License().name("Gaurav"))
                        );
    }
}