package uz.in.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BaseRepository<T,ID> {
    void save(T t);
    Optional<T> getById(ID id);
    T update(T t);
    int deleteByID(ID id);
    List<T> getAll();
}

