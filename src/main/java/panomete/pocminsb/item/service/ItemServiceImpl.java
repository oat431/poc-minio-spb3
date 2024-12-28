package panomete.pocminsb.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import panomete.pocminsb.item.entity.Item;
import panomete.pocminsb.item.payload.request.ItemRequest;
import panomete.pocminsb.item.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    @Override
    public Item registerItem(ItemRequest item) {
        Item newItem = Item.builder()
                .name(item.name())
                .description(item.description())
                .price(new BigDecimal(item.price()))
                .image(item.image())
                .build();
        return itemRepository.save(newItem);
    }

    @Override
    public Item getItem(UUID id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(UUID id, ItemRequest item) {
        Item itemToUpdate = itemRepository.findById(id).orElse(null);
        if (itemToUpdate == null) {
            return null;
        }
        itemToUpdate.setName(item.name());
        itemToUpdate.setDescription(item.description());
        itemToUpdate.setPrice(new BigDecimal(item.price()));
        itemToUpdate.setImage(item.image());
        return itemRepository.save(itemToUpdate);
    }

    @Override
    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }
}
