package uz.in.domain.dto.basket;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketCreateDto {
    UUID customerId;
    UUID productId;
    Integer amount;
    LocalDateTime boughtDate;
}
