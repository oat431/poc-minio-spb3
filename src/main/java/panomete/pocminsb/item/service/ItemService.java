package panomete.pocminsb.item.service;

import panomete.pocminsb.item.entity.Item;
import panomete.pocminsb.item.payload.request.ItemRequest;

import java.util.UUID;
import java.util.List;

public interface ItemService {
    Item registerItem(ItemRequest item);
    Item getItem(UUID id);
    List<Item> getItems();
    Item updateItem(UUID id, ItemRequest item);
    void deleteItem(UUID id);

}
