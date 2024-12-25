package panomete.pocminsb.item.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ItemRequest",
        description = "Request payload for item"
)
public record ItemRequest(
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
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
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
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String image
) {
}
