package uz.in.domain.dto.basket;

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
public class BasketReadDto {
    UUID id;
    UUID customerId;
    ProductCategory category;
    String name;
    String description;
    String maker;
    Double price;
    UUID sellerId;
    Integer amount;
    LocalDateTime boughtDate;
}
