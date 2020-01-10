package com.renga.services.user.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;


import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
public class SwaggerConfig {
    @Value("${api.common.version}")           String apiVersion;
    @Value("${api.common.title}")             String apiTitle;
    @Value("${api.common.description}")       String apiDescription;
    @Value("${api.common.termsOfServiceUrl}") String apiTermsOfServiceUrl;
    @Value("${api.common.license}")           String apiLicense;
    @Value("${api.common.licenseUrl}")        String apiLicenseUrl;
    @Value("${api.common.contact.name}")      String apiContactName;
    @Value("${api.common.contact.url}")       String apiContactUrl;
    @Value("${api.common.contact.email}")     String apiContactEmail;


	@Bean
	public Docket apiDocumentation() {
		return new Docket(SWAGGER_2)
			.select()
			.paths(PathSelectors.any())
			.build()
                .globalResponseMessage(GET, emptyList())
				.apiInfo(new ApiInfo(
                    apiTitle,
                    apiDescription,
                    apiVersion,
                    apiTermsOfServiceUrl,
                    new Contact(apiContactName, apiContactUrl, apiContactEmail),
                    apiLicense,
                    apiLicenseUrl,
                    emptyList()
                ));
    }

}