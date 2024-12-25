package panomete.pocminsb.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.pocminsb.item.entity.Item;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
