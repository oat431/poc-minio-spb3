package panomete.pocminsb.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import panomete.pocminsb.common.entity.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@Table(name = "tb_item")
public class Item extends BaseEntity {
    public Item () {}

    private String name;
    private String description;
    private BigDecimal price;
    private String image;


}
