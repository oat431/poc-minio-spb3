package panomete.pocminsb.item.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import panomete.pocminsb.common.dto.ResponseDto;
import panomete.pocminsb.item.entity.Item;
import panomete.pocminsb.item.payload.request.ItemRequest;
import panomete.pocminsb.item.payload.response.ItemDto;
import panomete.pocminsb.item.service.ItemService;
import panomete.pocminsb.utils.CloudStorageHelper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
@Tag(name = "Item API", description = "APIs for item")
public class ItemController {
    private final ItemService itemService;
    private final CloudStorageHelper cloudStorageHelper;

    @PostMapping
    @Operation(
            summary = "Register item",
            description = "Register a new item"
    )
    public ResponseEntity<ResponseDto<ItemDto>> registerItem(
            @RequestBody ItemRequest request
    ) {
        Item item = itemService.registerItem(request);
        ItemDto itemDto = toDto(item);
        ResponseDto<ItemDto> response = new ResponseDto<>(
                itemDto,
                "201",
                "Item registered successfully"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get items",
            description = "Get all items"
    )
    public ResponseEntity<ResponseDto<List<ItemDto>>> getItems() {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        toDto(itemService.getItems()),
                        "200",
                        "Items retrieved successfully"
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get item",
            description = "Get an item by id"
    )
    public ResponseEntity<ResponseDto<ItemDto>> getItem(
            @PathVariable(name = "id") String id
    ) {
        Item item = itemService.getItem(UUID.fromString(id));
        if (item == null) {
            return new ResponseEntity<>(
                    new ResponseDto<>(
                            null,
                            "404",
                            "Item not found"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                new ResponseDto<>(
                        toDto(item),
                        "200",
                        "Item retrieved successfully"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update item",
            description = "Update an item by id"
    )
    public ResponseEntity<ResponseDto<ItemDto>> updateItem(
            @PathVariable(name = "id") String id,
            @RequestBody ItemRequest request
    ) {
        Item item = itemService.updateItem(UUID.fromString(id), request);
        if (item == null) {
            return new ResponseEntity<>(
                    new ResponseDto<>(
                            null,
                            "404",
                            "Item not found"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                new ResponseDto<>(
                        toDto(item),
                        "200",
                        "Item updated successfully"
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete item",
            description = "Delete an item by id"
    )
    public ResponseEntity<ResponseDto<Void>> deleteItem(
            @PathVariable(name = "id") String id
    ) {
        itemService.deleteItem(UUID.fromString(id));
        String message = "Item id " + id + " deleted successfully";
        return new ResponseEntity<>(
                new ResponseDto<>(
                        null,
                        "204",
                        message
                ),
                HttpStatus.NO_CONTENT
        );
    }

    private List<ItemDto> toDto(List<Item> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice().toString(),
                cloudStorageHelper.getMetadata(item.getImage(), "panomete-storage"),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getVersion()
        );
    }
}
