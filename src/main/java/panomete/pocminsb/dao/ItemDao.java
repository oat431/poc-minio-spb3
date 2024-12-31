package panomete.pocminsb.dao;

import panomete.pocminsb.item.entity.Item;

import java.util.List;
import java.util.UUID;

public interface ItemDao {
    Item saveItem(Item item);
    Item getItem(UUID id);
    List<Item> getItems();
    void deleteItem(UUID id);
}
