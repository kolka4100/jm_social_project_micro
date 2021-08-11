package jm_social_project.media_storage.config;

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
                .apis(RequestHandlerSelectors.basePackage("jm_social_project.media_storage.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new  ApiInfo("Media Storage API",
                "A service saves videos and photos to the file system. Added counting of likes to a photo.",
                "1.0",
                "Terms of service",
                new Contact("Nikita Nesterenko", "https://gitlab.com/Nikitos1997", "myeaddress@company.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
        return apiInfo;
    }
}
