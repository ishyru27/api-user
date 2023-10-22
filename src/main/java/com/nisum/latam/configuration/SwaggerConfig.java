package com.nisum.latam.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Luis Carvajal","", "luiscarvajal2208@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
            .title("Nisum - API USER")
            .description(
                    "Api para la creacion de usuarios.")
            .version("V1.0.0").license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .contact(DEFAULT_CONTACT).build();

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
                .apis(RequestHandlerSelectors.basePackage("com.nisum.latam")).paths(regex("/user.*")).build();
    }
}
