package panomete.pocminsb.minio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import panomete.pocminsb.minio.dto.ImageMetadataDto;
import panomete.pocminsb.utils.CloudStorageHelper;

import java.io.InputStream;

@Controller
@RequiredArgsConstructor
@Tag(name = "Developer API", description = "APIs for dev")
public class DevController {
    private final CloudStorageHelper cloudStorageHelper;

    @Operation(
            summary = "Check Minio service",
            description = "Check if Minio service is running"
    )
    @GetMapping("/check-minio")
    public ResponseEntity<?> checkMinio() {
        cloudStorageHelper.checkService();
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Upload file",
            description = "Upload a file to Minio"
    )
    @PostMapping(
            value ="/upload-file",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<ImageMetadataDto> uploadFile(
             @Parameter(
                     name = "file",
                     description = "File to upload",
                     required = true,
                     content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
             )
             @RequestPart("file") MultipartFile file
    ) {
        ImageMetadataDto url = cloudStorageHelper.uploadFile(file, "panomete-storage");
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }

    @GetMapping("/pdf/{filename}")
    @Operation(summary = "open pdf in new tab")
    public ResponseEntity<InputStreamResource> getPdfFromMinIO(@PathVariable String filename) {
        try {
            InputStream pdfStream = cloudStorageHelper.getPdfMetadata(filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(pdfStream));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
