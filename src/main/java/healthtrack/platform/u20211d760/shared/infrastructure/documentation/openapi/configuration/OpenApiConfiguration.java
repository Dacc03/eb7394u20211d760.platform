package healthtrack.platform.u20211d760.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for API documentation.
 * Configures Swagger UI and OpenAPI specifications.
 * 
 * @author July Paico
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${documentation.application.description}")
    private String applicationDescription;

    @Value("${documentation.application.version}")
    private String applicationVersion;

    /**
     * Creates OpenAPI bean configuration
     * 
     * @return Configured OpenAPI instance
     */
    @Bean
    public OpenAPI healthTrackPlatformOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .description(applicationDescription)
                        .version(applicationVersion)
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("HealthTrack Platform Documentation")
                        .url("https://healthtrack.platform.docs"));
    }
}
