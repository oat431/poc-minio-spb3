package panomete.pocminsb.minio.dto;

public record ImageMetadataDto(
        String url,
        String name,
        Long lastModified,
        Long size,
        String type
) {
}
