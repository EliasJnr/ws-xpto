package com.eliasjr.wsxpto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class WsXptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsXptoApplication.class, args);
	}

	
}
