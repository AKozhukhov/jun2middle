package ru.itone.jun2middle.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itone.jun2middle.shop.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByFio(String fio);
    boolean existsById(UUID id);
    Optional<User> findById(UUID id);
}