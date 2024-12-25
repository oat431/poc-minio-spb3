package panomete.pocminsb.common.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import panomete.pocminsb.utils.CloudStorageHelper;

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

}
