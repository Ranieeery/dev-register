package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {
    private String name;
    private String description;
    private Languages language;
    private LocalDateTime startDate;
    private LocalDate dueDate;

    @JsonIgnore
    private Developer developer;
    private Status status;
    private LocalDate createdAt;
    private LocalDate finishDate;
}
