package br.com.phc.brasileiraoapi.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String BASE_PACKAGE = "br.com.phc.brasileiraoapi.controller";
	private static final String API_TITLE = "Brasileirão API";
	private static final String API_DESCRIPTION = "API para cadastro de jogos e times do campeonato Brasileiro";
	private static final String CONTACT_NAME = "Paulo Henrique da Cruz";
	private static final String CONTACT_GITHUB = "https://github.com/phcruz";
	private static final String CONTACT_EMAIL = "henryque_phc@yahoo.com.br";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any()).build().apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title(API_TITLE).description(API_DESCRIPTION).version("1.0.0")
				.contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL)).build();
	}
}
