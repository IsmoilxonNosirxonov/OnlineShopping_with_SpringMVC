package uz.in.repository.basket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uz.in.domain.entity.basket.BasketEntity;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BasketRepositoryImpl implements BasketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(BasketEntity basketEntity) {
        entityManager.persist(basketEntity);
    }

    @Override
    public List<BasketEntity> basketProducts(UUID userId) {
        return entityManager
                .createQuery("select b from baskets b where b.customerId=:userId", BasketEntity.class)
                .setParameter("userId",userId).getResultList();
    }
}
