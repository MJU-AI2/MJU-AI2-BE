package com.example.aiquiz.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
			.info(new Info().title("AI2 API")
				.description("인공지능2 프로젝트 API 명세")
				.version("1.0"));
	}

}
