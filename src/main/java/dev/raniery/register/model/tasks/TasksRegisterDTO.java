package dev.raniery.register.model.tasks;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksRegisterDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Languages language;

    private LocalDateTime startDate;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private Developer developer;

    @NotNull
    private Priority priority;
}
