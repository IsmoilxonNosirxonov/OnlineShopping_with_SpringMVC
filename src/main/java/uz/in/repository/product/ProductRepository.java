package uz.in.repository.product;

import org.springframework.stereotype.Repository;
import uz.in.domain.entity.product.ProductCategory;
import uz.in.domain.entity.product.ProductEntity;
import uz.in.repository.BaseRepository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, UUID> {
    List<ProductEntity> myProducts(UUID userId);
    List<ProductEntity> getProductsByCategory(ProductCategory productCategory);
}
