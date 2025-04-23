package dev.radom.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@ComponentScans({@ComponentScan("dev.radom.accounts.controller")})
//@EntityScan("dev.radom.accounts.entity")
//@EnableJpaRepositories("dev.radom.accounts.repository")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Accounts API",
				version = "1.0",
				description = "Documentation Accounts API v1.0",
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
						url = "http://localhost:8080",
						description = "Local server"
				),
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "https://staging.radomkheom.me/api/accounts",
						description = "Staging server"
				),
				@io.swagger.v3.oas.annotations.servers.Server(
						url = "https://radomkheom.me/api/accounts",
						description = "Production server"
				)
		},
		tags = {
				@io.swagger.v3.oas.annotations.tags.Tag(
						name = "Accounts",
						description = "Endpoints for managing accounts"
				)
		},
		externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
				description = "More about Radom",
				url = "https://radomkheom.me"
		)
)
@SpringBootApplication
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
