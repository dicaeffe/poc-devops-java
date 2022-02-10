package poc.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
/* The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled. */
@EnableScheduling
public class PocDevopsJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocDevopsJavaApplication.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("POC DevOps JAVA")
								.description("The POC DevOps Java aims to be a proof of concept for the DevOps implementation on Java microservices buildt with Spring boot libraries.")
								.termsOfService("https://www.more.info/about/termsAndConditions")
								.contact(new Contact().name("support").url("https://www.more.info/about/").email("write@to.me"))
								.license(new License().name("MyLicense 1.0").url("https://www.more.info/about/license"))
								.version("0.0.1")
					)
				.externalDocs(new ExternalDocumentation()
								.description("SpringShop Wiki Documentation")
								.url("https://springshop.wiki.github.org/docs"));
	}

}
