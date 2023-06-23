package uz.in.repository.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uz.in.domain.entity.product.ProductCategory;
import uz.in.domain.entity.product.ProductEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(ProductEntity productEntity) {
        entityManager.persist(productEntity);
    }

    @Override
    public Optional<ProductEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(ProductEntity.class, id));
    }

    @Override
    @Transactional
    public ProductEntity update(ProductEntity productEntity) {
        return entityManager.merge(productEntity);
    }

    @Override
    @Transactional
    public int deleteByID(UUID id) {
        return entityManager
                .createQuery("delete from products where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<ProductEntity> getAll() {
        return entityManager
                .createQuery("select p from products p", ProductEntity.class).getResultList();
    }

    @Override
    public List<ProductEntity> myProducts(UUID userId) {
        return entityManager
                .createQuery("select p from products p where p.sellerId=:userId", ProductEntity.class)
                .setParameter("userId",userId).getResultList();
    }

    @Override
    public List<ProductEntity> getProductsByCategory(ProductCategory productCategory) {
        return entityManager
                .createQuery("select p from products p where p.category=:category", ProductEntity.class)
                .setParameter("category",productCategory)
                .getResultList();
    }
}
