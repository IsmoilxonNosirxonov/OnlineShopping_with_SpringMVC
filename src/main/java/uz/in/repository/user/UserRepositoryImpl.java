package uz.in.repository.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uz.in.domain.entity.user.UserEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(UserEntity userEntity) {
        userEntity.setAktive(true);
        entityManager.persist(userEntity);
    }

    @Override
    public Optional<UserEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public UserEntity update(UserEntity userEntity) {
        return entityManager.merge(userEntity);
    }

    @Override
    @Transactional
    public int deleteByID(UUID id) {
        return entityManager
                .createQuery("delete from users where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<UserEntity> getAll() {
        return entityManager
                .createQuery("select u from users u where u.role!='ADMIN'", UserEntity.class).getResultList();
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        UserEntity userEntity = entityManager
                .createQuery("select u from users u where email=:email", UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();

        if(userEntity != null) {
            return Optional.of(userEntity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> signIn(String email,String password) {
        UserEntity userEntity = entityManager
                .createQuery("select u from users u where email=:email and password=:password", UserEntity.class)
                .setParameter("email", email)
                .setParameter("password",password)
                .getSingleResult();

        if(userEntity != null) {
            return Optional.of(userEntity);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void block(UUID id) {
        entityManager
                .createQuery("update users set isAktive = false where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void unBlock(UUID id) {
        entityManager
                .createQuery("update users set isAktive = true where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void fillBalance(UUID id, Double amount) {
        entityManager
                .createQuery("update users set balance=balance+:amount where id=:id")
                .setParameter("amount",amount)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateBalance(UUID id, Double amount,boolean add) {
        if (add){
            entityManager
                    .createQuery("update users set balance=balance+:amount where id=:id")
                    .setParameter("amount",amount)
                    .setParameter("id", id)
                    .executeUpdate();
        }
        else {
            entityManager
                    .createQuery("update users set balance=balance-:amount where id=:id")
                    .setParameter("amount",amount)
                    .setParameter("id", id)
                    .executeUpdate();
        }
    }

}
