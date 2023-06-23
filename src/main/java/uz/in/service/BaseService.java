package uz.in.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * @param <CD> This is Creation DTO
 * @param <RD> This is Read DTO
 * @author Nosirxonov Ismoilxon
 */
@Service
public interface BaseService<CD, RD> {
    int add(CD cd);

    RD getById(UUID id);

    void update (RD rd);

    int delete(UUID id);

    List<RD> getAll();
}
