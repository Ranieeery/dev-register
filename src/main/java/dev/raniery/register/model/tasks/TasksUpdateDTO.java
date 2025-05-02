package dev.raniery.register.model.tasks;

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
public class TasksUpdateDTO {
    private String name;
    private String description;
    private Languages language;
    private LocalDateTime startDate;
    private LocalDate dueDate;
    private Developer developer;
    private Status status;
    private LocalDate finishDate;
    private Priority priority;

    public boolean hasName() {
        return name != null;
    }

    public boolean hasDescription() {
        return description != null;
    }

    public boolean hasLanguage() {
        return language != null;
    }

    public boolean hasStartDate() {
        return startDate != null;
    }

    public boolean hasDueDate() {
        return dueDate != null;
    }

    public boolean hasDeveloper() {
        return developer != null;
    }

    public boolean hasStatus() {
        return status != null;
    }

    public boolean hasFinishDate() {
        return finishDate != null;
    }

    public boolean hasPriority() {
        return priority != null;
    }
}
