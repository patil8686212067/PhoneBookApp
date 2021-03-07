package com.ashokit.phonebook.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket restApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.ashokit"))
		.paths(PathSelectors.any())
		.build();
	}
  
	

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Phone Book Application")
				.description("Phone Book Application Developed by Ashok IT School ")
				.termsOfServiceUrl("http://ashokit.in")
				.contact("Contact.class").license("AshokIT License")
				.licenseUrl("anilrspatil1992@gmail.com").version("1.0").build();
	}
}
