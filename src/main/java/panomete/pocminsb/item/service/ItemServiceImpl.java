package panomete.pocminsb.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import panomete.pocminsb.dao.ItemDao;
import panomete.pocminsb.item.entity.Item;
import panomete.pocminsb.item.payload.request.ItemRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemDao itemDao;

    @Override
    public Item registerItem(ItemRequest item) {
        Item newItem = Item.builder()
                .name(item.name())
                .description(item.description())
                .price(new BigDecimal(item.price()))
                .image(item.image())
                .build();
        return itemDao.saveItem(newItem);
    }

    @Override
    public Item getItem(UUID id) {
        return itemDao.getItem(id);
    }

    @Override
    public List<Item> getItems() {
        return itemDao.getItems();
    }

    @Override
    public Item updateItem(UUID id, ItemRequest item) {
        Item itemToUpdate = itemDao.getItem(id);
        if (itemToUpdate == null) {
            return null;
        }
        itemToUpdate.setName(item.name());
        itemToUpdate.setDescription(item.description());
        itemToUpdate.setPrice(new BigDecimal(item.price()));
        itemToUpdate.setImage(item.image());
        return itemDao.saveItem(itemToUpdate);
    }

    @Override
    public void deleteItem(UUID id) {
        itemDao.deleteItem(id);
    }
}
