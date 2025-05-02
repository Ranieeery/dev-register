package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import jakarta.persistence.*;
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

    private boolean completed;

    public Tasks(TasksRegisterDTO registerDTO) {
        this.name = registerDTO.getName();
        this.description = registerDTO.getDescription();
        this.language = registerDTO.getLanguages();
        this.dueDate = registerDTO.getDueDate();
        this.developer = registerDTO.getDeveloper();
        this.status = Status.TO_DO;
        this.createdAt = LocalDate.now();
    }
}
