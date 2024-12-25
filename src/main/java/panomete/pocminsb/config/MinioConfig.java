package panomete.pocminsb.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfig {
    @Value("${minio.host}")
    private String host;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient createMinioClient() {
        try {
            MinioClient minioClient = null;
            minioClient = MinioClient.builder()
                    .endpoint(host)
                    .credentials(accessKey, secretKey)
                    .build();
            return minioClient;
        } catch (Exception e) {
            throw new RuntimeException("Error while creating Minio client: " + e.getMessage());
        }
    }
}
