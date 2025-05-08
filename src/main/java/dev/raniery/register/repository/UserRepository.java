package dev.raniery.register.repository;

import dev.raniery.register.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Boolean existsByEmail(String email);

    Optional<UserDetails> findUsersByEmail(String email);
}
