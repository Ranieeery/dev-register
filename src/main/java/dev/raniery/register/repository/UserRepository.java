package dev.raniery.register.repository;

import dev.raniery.register.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
