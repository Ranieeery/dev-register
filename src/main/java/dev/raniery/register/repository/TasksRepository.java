package dev.raniery.register.repository;

import dev.raniery.register.model.tasks.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    @Query("""
        SELECT t FROM Tasks t
        WHERE t.completed = false
    """)
    Page<Tasks> findAllByCompletedTrue(Pageable pageable);
}
