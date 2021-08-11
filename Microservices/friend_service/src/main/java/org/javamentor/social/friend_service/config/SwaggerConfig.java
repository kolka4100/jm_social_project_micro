package org.javamentor.social.friend_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("org.javamentor.social.friend_service.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new  ApiInfo("Friend Service API",
                "A service which is used to send, cancel, accept and decline friend requests.",
                "1.0",
                "Terms of service",
                new Contact("Nikita Nesterenko", "https://gitlab.com/Nikitos1997", "myeaddress@company.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
        return apiInfo;
    }
}
