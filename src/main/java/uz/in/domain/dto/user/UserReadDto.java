package uz.in.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.in.domain.entity.user.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserReadDto{
    UUID id;
    String name;
    String email;
    String password;
    Double balance;
    UserRole role;
    boolean isAktive;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;
}
