package uz.in.domain.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.in.domain.entity.product.ProductCategory;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReadDto {
    UUID id;
    ProductCategory category;
    String name;
    String description;
    String maker;
    Double price;
    UUID sellerId;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
