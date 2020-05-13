package com.edu.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket jobMatchingAPI() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.forCodeGeneration(true)
				.apiInfo(ApiInfo.DEFAULT)
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.edu.backend.controller"))                          
				.build()
				.apiInfo(metadata())
				.useDefaultResponseMessages(false)
				.select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))) 
				.build();
	}
	
	private ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("MB API")
				.version("1.0.0")//
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
				.contact(new Contact("Elly Businge", "xxx", "ell86bu@gmail.com"))//
				.build();
	}
}