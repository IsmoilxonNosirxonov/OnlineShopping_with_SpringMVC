package uz.in.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(name = "created_date")
    @CreationTimestamp
    protected LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    protected LocalDateTime updatedDate;
}
