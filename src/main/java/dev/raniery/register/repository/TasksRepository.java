package dev.raniery.register.repository;

import dev.raniery.register.model.tasks.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
