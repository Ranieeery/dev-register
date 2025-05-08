package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for complete representation of a task")
public class TasksDTO {
    @Schema(description = "Task's unique identifier", example = "1")
    private Long id;

    @Schema(description = "Task name", example = "Implement JWT Authentication")
    private String name;

    @Schema(description = "Detailed task description", example = "Implement authentication system using JWT tokens")
    private String description;

    @Schema(description = "Main language used for the task")
    private Languages language;

    @Schema(description = "Start date and time of the task", example = "2025-05-08T14:30:00")
    private LocalDateTime startDate;

    @Schema(description = "Expected delivery date", example = "2025-05-15")
    private LocalDate dueDate;

    @JsonIgnore
    private Developer developer;

    @Schema(description = "Current task status", example = "IN_PROGRESS")
    private Status status;

    @Schema(description = "Task creation date", example = "2025-05-01")
    private LocalDate createdAt;

    @Schema(description = "Task completion date", example = "2025-05-10")
    private LocalDate finishDate;

    @Schema(description = "Indicates if the task is completed", example = "false")
    private Boolean completed;

    @Schema(description = "Task priority", example = "HIGH")
    private Priority priority;

    @JsonProperty("developer")
    @Schema(description = "ID of the developer responsible for the task")
    public DeveloperId getDeveloperId() {
        return new DeveloperId(developer != null ? developer.getId() : null);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Representation of developer ID")
    public static class DeveloperId {
        @Schema(description = "Developer's unique ID", example = "123e4567-e89b-12d3-a456-426614174000")
        private UUID id;
    }
}
