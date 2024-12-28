package panomete.pocminsb.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import panomete.pocminsb.common.entity.BaseEntity;
import panomete.pocminsb.minio.dto.ImageMetadataDto;
import panomete.pocminsb.utils.CloudStorageHelper;

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
