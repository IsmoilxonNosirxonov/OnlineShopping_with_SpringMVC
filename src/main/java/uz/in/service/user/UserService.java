package uz.in.service.user;

import org.springframework.stereotype.Service;
import uz.in.domain.dto.user.UserCreateDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.service.BaseService;
import java.util.UUID;


@Service
public interface UserService extends BaseService<UserCreateDto, UserReadDto> {
    UserReadDto signIn(String email, String password);
    void block(UUID id);
    void unBlock(UUID id);
    void fillBalance(UUID id,Double amount);
}
