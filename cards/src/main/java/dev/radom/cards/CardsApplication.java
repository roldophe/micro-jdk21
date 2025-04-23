package dev.radom.cards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Cards API",
				version = "1.0",
				description = "Documentation Cards API v1.0",
				contact = @io.swagger.v3.oas.annotations.info.Contact(
						name = "Radom",
						email = "radomkheom@me.com",
						url = "https://radomkheom.me"
				),
				termsOfService = "https://radomkheom.me/terms",
				license = @io.swagger.v3.oas.annotations.info.License(
						name = "MIT License",
						url = "https://radomkheom.me/license"
				)
		),
		servers = {
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "http://localhost:8081",
						description = "Local server"
				),
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "https://staging.radomkheom.me/api/cards",
						description = "Staging server"
				),
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "https://radomkheom.me/api/cards",
						description = "Production server"
				)
		},
		tags = {
				@io.swagger.v3.oas.annotations.tags.Tag(
						name = "Cards",
						description = "Endpoints for managing cards"
				)
		},
		externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
				description = "More about Radom",
				url = "https://radomkheom.me"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
