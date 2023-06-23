package uz.in.domain.entity.basket;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "baskets")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(name = "customer_id",nullable = false)
    @NonNull
    private UUID customerId;

    @Column(name = "product_id",nullable = false)
    @NonNull
    private UUID productId;

    @Column(nullable = false)
    @NonNull
    @Positive
    private Integer amount;

    @Column(name = "bought_date",nullable = false)
    @NonNull
    protected LocalDateTime boughtDate;
}
