package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import dev.raniery.register.repository.DeveloperRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Languages language;

    private LocalDateTime startDate;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    @JsonIgnore
    private Developer developer;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate createdAt;

    private LocalDate finishDate;

    private Boolean completed;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Boolean active;

    public Tasks(TasksRegisterDTO registerDTO) {
        this.name = registerDTO.getName();
        this.description = registerDTO.getDescription();
        this.language = registerDTO.getLanguage();
        this.dueDate = registerDTO.getDueDate();
        this.developer = registerDTO.getDeveloper();
        this.status = Status.TO_DO;
        this.createdAt = LocalDate.now();
        this.priority = registerDTO.getPriority();
        this.completed = false;
        this.active = true;
    }

    public void setCompletedStatus(Status status) {
        this.completed = status.equals(Status.COMPLETED);
    }

    public void setActive() {
        this.active = !active;
    }

    public void updateTask(@Valid TasksUpdateDTO updateDTO, DeveloperRepository developerRepository) {
        if (updateDTO.hasName()) {
            this.name = updateDTO.getName();
        }
        if (updateDTO.hasDescription()) {
            this.description = updateDTO.getDescription();
        }
        if (updateDTO.hasLanguage()) {
            this.language = updateDTO.getLanguage();
        }
        if (updateDTO.hasStartDate()) {
            this.startDate = updateDTO.getStartDate();
        }
        if (updateDTO.hasDueDate()) {
            this.dueDate = updateDTO.getDueDate();
        }
        if (updateDTO.hasDeveloperId()) {
            this.developer = developerRepository.findById(updateDTO.getDeveloperId().getId()).orElse(this.developer);
        }
        if (updateDTO.hasStatus()) {
            Status newStatus = updateDTO.getStatus();
            this.status = newStatus;
            this.setCompletedStatus(newStatus);
        }
        if (updateDTO.hasFinishDate()) {
            this.finishDate = updateDTO.getFinishDate();
        }
        if (updateDTO.hasPriority()) {
            this.priority = updateDTO.getPriority();
        }
    }
}
