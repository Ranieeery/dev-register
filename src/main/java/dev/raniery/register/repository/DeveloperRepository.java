package dev.raniery.register.repository;

import dev.raniery.register.model.developer.Developer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
    Page<Developer> findAllByActiveTrue(Pageable pageable);

    Optional<Developer> findByIdAndActiveTrue(UUID id);

    Optional<Developer> findByIdAndActiveFalse(UUID id);

    Boolean existsByIdAndActiveTrue(UUID id);
}
