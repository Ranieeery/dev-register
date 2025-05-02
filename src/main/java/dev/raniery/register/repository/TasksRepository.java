package dev.raniery.register.repository;

import dev.raniery.register.model.tasks.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    @Query("""
            SELECT t FROM Tasks t
            WHERE t.completed = false
            AND t.active = true
        """)
    Page<Tasks> findAllByCompletedTrue(Pageable pageable);

    Optional<Tasks> findByIdAndActiveTrue(Long id);

    Optional<Tasks> findByIdAndActiveFalse(Long id);
}
