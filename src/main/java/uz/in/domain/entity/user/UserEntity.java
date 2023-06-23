package uz.in.domain.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jdk.jfr.BooleanFlag;
import lombok.*;
import uz.in.domain.entity.BaseEntity;

@Getter
@Setter
@Builder
@Entity(name = "users")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(nullable = false,unique = true)
    @NotBlank(message = "name cannot be empty or null")
    @NonNull
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "password cannot be empty or null")
    @NonNull
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "email cannot be empty or null")
    @NonNull
    @Email(message = "enter valid email address")
    private String email;

    @Column(nullable = false)
    @NonNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    @NonNull
    @Positive
    private Double balance;

    @Column(name = "is_aktive",nullable = false)
    @NonNull
    private boolean isAktive;
}
