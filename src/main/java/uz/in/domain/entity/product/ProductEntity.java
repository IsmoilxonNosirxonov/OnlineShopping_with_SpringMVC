package uz.in.domain.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import uz.in.domain.entity.BaseEntity;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "products")
@NoArgsConstructor(force = true)

public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "name cannot be empty or null")
    @NonNull
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "description cannot be empty or null")
    @NonNull
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "maker cannot be empty or null")
    @NonNull
    private String maker;

    @Column(nullable = false)
    @NonNull
    @Positive
    private Double price;

    @Column(name = "seller_id",nullable = false)
    @NonNull
    private UUID sellerId;
}
