package com.eliasjr.wsxpto.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuração do Swagger para consultar a API através de um browser e para
 * gerar API para o FRONT
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	

	  private static final String SWAGGER_API_VERSION = "1.0";
	    private static final String LICENSE_TEXT = "License";
	    private static final String title = "XPTO";
	    private static final String description = "System RESTful ENDPOINTS";

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title(title)
	                .description(description)
	                .license(LICENSE_TEXT)
	                .version(SWAGGER_API_VERSION)
	                .build();
	    }

	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .pathMapping("/")
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.eliasjr.wsxpto.controller"))
	                .paths(PathSelectors.regex("/api.*"))
	                .build();
	    }

}
