package uz.in.service.product;

import org.springframework.stereotype.Service;
import uz.in.domain.dto.product.ProductCreateDto;
import uz.in.domain.dto.product.ProductReadDto;
import uz.in.domain.entity.product.ProductCategory;
import uz.in.service.BaseService;
import java.util.List;
import java.util.UUID;

@Service
public interface ProductService extends BaseService<ProductCreateDto, ProductReadDto> {
    List<ProductReadDto> myProducts(UUID userId);
    List<ProductReadDto> getProductsByCategory(ProductCategory productCategory);
}
