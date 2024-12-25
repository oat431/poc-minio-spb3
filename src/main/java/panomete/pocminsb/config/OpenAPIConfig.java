package panomete.pocminsb.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "POC Minio Spring Boot 3",
                version = "0.1-dev",
                description = "POC Minio Spring Boot 3"
        )
)
public class OpenAPIConfig {
}
