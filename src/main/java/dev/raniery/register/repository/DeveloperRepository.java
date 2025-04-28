package dev.raniery.register.repository;

import dev.raniery.register.model.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
}
