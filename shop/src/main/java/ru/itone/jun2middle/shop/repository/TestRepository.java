package ru.itone.jun2middle.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itone.jun2middle.shop.model.entity.Test;

import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {
}
