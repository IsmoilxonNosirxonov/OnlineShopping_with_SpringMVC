package uz.in.repository.user;

import org.springframework.stereotype.Repository;
import uz.in.domain.entity.user.UserEntity;
import uz.in.repository.BaseRepository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<UserEntity,UUID> {

    Optional<UserEntity> getByEmail(String email);

    Optional<UserEntity> signIn(String email,String password);
    void block(UUID id);
    void unBlock(UUID id);
    void fillBalance(UUID id,Double amount);
    void updateBalance(UUID id,Double amount,boolean add);
}
