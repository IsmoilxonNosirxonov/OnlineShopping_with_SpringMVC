package uz.in.service.basket;

import org.springframework.stereotype.Service;
import uz.in.domain.dto.basket.BasketCreateDto;
import uz.in.domain.dto.basket.BasketReadDto;
import java.util.List;
import java.util.UUID;

@Service
public interface BasketService {
    int add(BasketCreateDto basketCreateDto);

    List<BasketReadDto> myProducts(UUID userId);
}
