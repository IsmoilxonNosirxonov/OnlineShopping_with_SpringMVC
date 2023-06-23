package uz.in.service.basket;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.in.domain.dto.basket.BasketCreateDto;
import uz.in.domain.dto.basket.BasketReadDto;
import uz.in.domain.entity.basket.BasketEntity;
import uz.in.domain.entity.product.ProductEntity;
import uz.in.repository.basket.BasketRepository;
import uz.in.repository.product.ProductRepository;
import uz.in.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public int add(BasketCreateDto basketCreateDto) {
        Double customerBalance = userRepository.getById(basketCreateDto.getCustomerId()).get().getBalance();
        double totalPrice = productRepository.getById(basketCreateDto.getProductId()).get().getPrice() * basketCreateDto.getAmount();
        if(customerBalance>=totalPrice){
            BasketEntity map = modelMapper.map(basketCreateDto, BasketEntity.class);
            basketRepository.save(map);
            userRepository.updateBalance(userRepository.getByEmail("admin@gmail.com").get().getId(),totalPrice/100,true);
            userRepository.updateBalance(productRepository.getById(basketCreateDto.getProductId()).get().getSellerId(),(totalPrice-totalPrice/100),true);
            userRepository.updateBalance(basketCreateDto.getCustomerId(),totalPrice,false);
            return 200;
        }
        return 500;
    }

    @Override
    public List<BasketReadDto> myProducts(UUID userId) {
        List<BasketEntity> baskets = basketRepository.basketProducts(userId);
        if(baskets.isEmpty()){
            return null;
        }

        List<BasketReadDto> myProducts=new ArrayList<>();
        for (BasketEntity basket : baskets) {
            ProductEntity product = productRepository.getById(basket.getProductId()).get();

            BasketReadDto basketReadDto =new BasketReadDto();
            basketReadDto.setId(basket.getId());
            basketReadDto.setCustomerId(basket.getCustomerId());
            basketReadDto.setCategory(product.getCategory());
            basketReadDto.setName(product.getName());
            basketReadDto.setDescription(product.getDescription());
            basketReadDto.setMaker(product.getMaker());
            basketReadDto.setPrice(product.getPrice());
            basketReadDto.setSellerId(product.getSellerId());
            basketReadDto.setAmount(basket.getAmount());
            basketReadDto.setBoughtDate(basket.getBoughtDate());

            myProducts.add(basketReadDto);
        }
        return myProducts;
    }
}
