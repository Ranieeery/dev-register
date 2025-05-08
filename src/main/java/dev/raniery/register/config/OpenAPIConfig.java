package dev.raniery.register.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Dev Register API")
                .description("API for developer and task management")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Raniery")
                    .url("https://github.com/Ranieeery"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")));
    }
}