package uz.in.service.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uz.in.domain.dto.product.ProductCreateDto;
import uz.in.domain.dto.product.ProductReadDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.domain.entity.product.ProductCategory;
import uz.in.domain.entity.product.ProductEntity;
import uz.in.domain.exception.DublicateDataValueException;
import uz.in.repository.product.ProductRepository;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public int add(ProductCreateDto productCreateDto) {
        try {
            productRepository.save(modelMapper.map(productCreateDto, ProductEntity.class));
        }catch (DublicateDataValueException e){
            return 500;
        }
        return 200;
    }

    @Override
    public ProductReadDto getById(UUID id) {
        Optional<ProductEntity> byId = productRepository.getById(id);
        if (byId.isPresent()){
            return modelMapper.map(byId, ProductReadDto.class);
        }
        return null;
    }

    @Override
    public void update(ProductReadDto productReadDto) {
        productRepository.update(modelMapper.map(productReadDto, ProductEntity.class));
    }

    @Override
    public int delete(UUID id) {
        return productRepository.deleteByID(id);
    }

    @Override
    public List<ProductReadDto> getAll() {
        return modelMapper.map(productRepository.getAll(),new TypeToken<List<ProductReadDto>>() {}.getType());
    }

    @Override
    public List<ProductReadDto> myProducts(UUID userId) {
        return modelMapper.map(productRepository.myProducts(userId),new TypeToken<List<ProductReadDto>>() {}.getType());
    }

    @Override
    public List<ProductReadDto> getProductsByCategory(ProductCategory productCategory) {
        return modelMapper.map(productRepository.getProductsByCategory(productCategory),new TypeToken<List<ProductReadDto>>() {}.getType());
    }
}
