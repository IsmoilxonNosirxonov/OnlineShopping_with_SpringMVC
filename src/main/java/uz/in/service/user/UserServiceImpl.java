package uz.in.service.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import uz.in.domain.dto.user.UserCreateDto;
import uz.in.domain.dto.user.UserReadDto;
import uz.in.domain.entity.user.UserEntity;
import uz.in.domain.exception.DublicateDataValueException;
import uz.in.repository.user.UserRepository;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public int add(UserCreateDto userCreateDto) {
        try {
            userRepository.save(modelMapper.map(userCreateDto, UserEntity.class));
            return 200;
        }catch (DublicateDataValueException e){
            return 500;
        }
    }

    @Override
    public UserReadDto getById(UUID id) {
        Optional<UserEntity> byId = userRepository.getById(id);
        if (byId.isPresent()){
            return modelMapper.map(byId,UserReadDto.class);
        }
        return null;
    }

    @Override
    public void update(UserReadDto userReadDto) {
        userRepository.update(modelMapper.map(userReadDto, UserEntity.class));
    }

    @Override
    public int delete(UUID id) {
        return userRepository.deleteByID(id);
    }

    @Override
    public List<UserReadDto> getAll() {
        return modelMapper.map(userRepository.getAll(), new TypeToken<List<UserReadDto>>() {}.getType() );
    }

    @Override
    public UserReadDto signIn(String email, String password) {
        Optional<UserEntity> signIn = userRepository.signIn(email, password);
        if (signIn.isPresent()){
            return modelMapper.map(signIn,UserReadDto.class);
        }
        return null;
    }

    @Override
    public void block(UUID id) {
        userRepository.block(id);
    }

    @Override
    public void unBlock(UUID id) {
        userRepository.unBlock(id);
    }

    @Override
    public void fillBalance(UUID id, Double amount) {
        userRepository.fillBalance(id,amount);
    }
}
