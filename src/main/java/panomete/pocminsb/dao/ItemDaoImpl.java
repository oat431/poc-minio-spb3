package panomete.pocminsb.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import panomete.pocminsb.item.entity.Item;
import panomete.pocminsb.item.repository.ItemRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {
    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
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
    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }
}
