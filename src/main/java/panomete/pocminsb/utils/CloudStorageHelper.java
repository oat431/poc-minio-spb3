package panomete.pocminsb.utils;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import panomete.pocminsb.minio.dto.ImageMetadataDto;

import java.io.InputStream;
import java.util.Objects;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudStorageHelper {
    private final MinioClient minioClient;
    public void checkService() {
        try {
            boolean isRunning = minioClient.bucketExists(BucketExistsArgs.builder().bucket("panomete-storage").build());
            if (isRunning) {
                log.info("Minio service is running");
            } else {
                log.error("Minio service is not running");
            }
        } catch (Exception e) {
            log.error("Error while checking Minio service: {}", e.getMessage());
        }
    }

    public ImageMetadataDto uploadFile(MultipartFile file, String bucketName) {
        try {
            String filename = getFileName(file.getOriginalFilename());
            uploadToMinio(filename, file, bucketName);
            StatObjectResponse stat = getStatusObject(filename, bucketName);
            String preSignedUrl = generatePreSignedUrl(filename, bucketName);
            return new ImageMetadataDto(
                    preSignedUrl,
                    filename,
                    stat.lastModified().toEpochSecond(),
                    stat.size(),
                    stat.contentType()
            );
        } catch (Exception e) {
            log.error("Error while uploading file: {}", e.getMessage());
            throw new RuntimeException("Error while uploading file: " + e.getMessage());
        }
    }

    public String getFileName(String filename) {
        return String.valueOf(System.currentTimeMillis())
                .concat("_")
                .concat("SCFC_STORAGE")
                .concat("_")
                .concat(Objects.requireNonNull(filename));
    }

    public void uploadToMinio(String filename, MultipartFile file, String bucketName) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();
        minioClient.putObject(args);
    }

    public StatObjectResponse getStatusObject(String filename,String bucketName) throws Exception {
        StatObjectArgs statObjectArgs = StatObjectArgs.builder()
                .bucket("panomete-storage")
                .object(filename)
                .build();
        return minioClient.statObject(statObjectArgs);
    }

    public String generatePreSignedUrl(String filename,String bucketName) throws Exception {
        GetPresignedObjectUrlArgs purl = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket("panomete-storage")
                .object(filename)
                .expiry(60 * 60 * 24)
                .build();
        return minioClient.getPresignedObjectUrl(purl);
    }

    public ImageMetadataDto getMetadata(String filename, String bucketName) {
        try {
            StatObjectResponse stat = getStatusObject(filename, bucketName);
            String preSignedUrl = generatePreSignedUrl(filename, bucketName);
            if(stat.contentType().equals("application/pdf")) {
                String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
                return new ImageMetadataDto(
                        homeURL + "/pdf/" + filename,
                        filename,
                        stat.lastModified().toEpochSecond(),
                        stat.size(),
                        stat.contentType()
                );
            }
            return new ImageMetadataDto(
                    preSignedUrl,
                    filename,
                    stat.lastModified().toEpochSecond(),
                    stat.size(),
                    stat.contentType()
            );
        } catch (Exception e) {
            log.error("Error while getting metadata: {}", e.getMessage());
            throw new RuntimeException("Error while getting metadata: " + e.getMessage());
        }
    }

    public InputStream getPdfMetadata(String filename) {
        try{
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("panomete-storage")
                            .object(filename)
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error while getting metadata: " + e.getMessage());
        }
    }

}


