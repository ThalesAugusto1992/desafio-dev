package com.example.desafiocnab.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * @return A builder which is intended to be the primary interface into the Springfox framework.
     * Provides sensible defaults and convenience methods for configuration.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    /**
     * @return API Information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Desafio CNAB REST")
                .description("Serviços para Desafio do CNAB")
                .version("0.0.1")
                .license("--")
                .licenseUrl("--")
                .contact(new Contact("Thales", "", "thales_augusto2007@hotmail.com"))
                .build();
    }
}
