package panomete.pocminsb.utils;

import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import panomete.pocminsb.config.MinioConfig;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudStorageHelper {
    private final MinioClient minioClient;
    public void checkService() {
        try {
            boolean isRunning = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test-bucket").build());
            if (isRunning) {
                log.info("Minio service is running");
            } else {
                log.error("Minio service is not running");
            }
        } catch (Exception e) {
            log.error("Error while checking Minio service: {}", e.getMessage());
        }
    }

    public void uploadFile() {

    }
}
