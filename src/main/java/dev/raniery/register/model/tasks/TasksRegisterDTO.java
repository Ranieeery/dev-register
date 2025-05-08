package dev.raniery.register.model.tasks;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "DTO for registering a new task")
public class TasksRegisterDTO {

    @NotBlank
    @Schema(description = "Task name", example = "Implement JWT Authentication")
    private String name;

    @NotBlank
    @Schema(description = "Detailed task description", example = "Implement authentication system using JWT tokens")
    private String description;

    @NotNull
    @Schema(description = "Main language used for the task")
    private Languages language;

    @Schema(description = "Start date and time of the task", example = "2025-05-08T14:30:00")
    private LocalDateTime startDate;

    @NotNull
    @Schema(description = "Expected delivery date", example = "2025-05-15")
    private LocalDate dueDate;

    @NotNull
    @Schema(description = "Developer responsible for the task")
    private Developer developer;

    @NotNull
    @Schema(description = "Task priority")
    private Priority priority;
}
