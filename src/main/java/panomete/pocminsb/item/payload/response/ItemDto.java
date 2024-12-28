package panomete.pocminsb.item.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import panomete.pocminsb.minio.dto.ImageMetadataDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(
        name = "ItemDto",
        description = "Response payload for item"
)
public record ItemDto (
        @Schema(
                name = "id",
                description = "Unique identifier of the item",
                example = "550e8400-e29b-41d4-a716-446655440000",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        UUID id,

        @Schema(
                name = "name",
                description = "Name of the item",
                example = "Scissors",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String name,

        @Schema(
                name = "description",
                description = "Description of the item",
                example = "A pair of scissors",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String description,

        @Schema(
                name = "price",
                description = "Price of the item",
                example = "10.00",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String price,

        @Schema(
                name = "image",
                description = "Image of the item",
                example = "https://example.com/scissors.jpg",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        ImageMetadataDto imageMetadata,

        @Schema(
                name = "createdAt",
                description = "Date and time when the item was created",
                example = "2021-08-01T12:00:00",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDateTime createdAt,

        @Schema(
                name = "updatedAt",
                description = "Date and time when the item was last updated",
                example = "2021-08-01T12:00:00",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDateTime updatedAt,

        @Schema(
                name = "version",
                description = "Version of the item",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long version
) {

}
