package uz.in.repository.basket;

import org.springframework.stereotype.Repository;
import uz.in.domain.entity.basket.BasketEntity;
import java.util.List;
import java.util.UUID;

@Repository
public interface BasketRepository {

    void save(BasketEntity basketEntity);

    List<BasketEntity> basketProducts(UUID userId);
}
