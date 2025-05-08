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
@Schema(description = "DTO for updating task data")
public class TasksUpdateDTO {
    @Schema(description = "Task's unique identifier", example = "1")
    private Long id;

    @Schema(description = "Task name (optional)", example = "Implement JWT Authentication v2")
    private String name;

    @Schema(description = "Detailed task description (optional)", example = "Implement authentication system using JWT tokens with refresh token")
    private String description;

    @Schema(description = "Main language used for the task (optional)")
    private Languages language;

    @Schema(description = "Start date and time of the task (optional)", example = "2025-05-09T10:00:00")
    private LocalDateTime startDate;

    @Schema(description = "Expected delivery date (optional)", example = "2025-05-20")
    private LocalDate dueDate;

    @JsonIgnore
    private Developer developer;

    @Schema(description = "Current task status (optional)", example = "IN_PROGRESS")
    private Status status;

    @Schema(description = "Task creation date (optional)", example = "2025-05-01")
    private LocalDate createdAt;

    @Schema(description = "Task completion date (optional)", example = "2025-05-18")
    private LocalDate finishDate;

    @Schema(description = "Indicates if the task is completed (optional)", example = "false")
    private Boolean completed;

    @Schema(description = "Task priority (optional)", example = "URGENT")
    private Priority priority;

    @JsonProperty("developer")
    @Schema(description = "ID of the developer responsible for the task (optional)")
    public TasksUpdateDTO.DeveloperId getDeveloperId() {
        return new TasksUpdateDTO.DeveloperId(developer != null ? developer.getId() : null);
    }

    @JsonProperty("developer")
    public void setDeveloperId(DeveloperId developerId) {
        if (developerId != null) {
            Developer dev = new Developer();
            dev.setId(developerId.getId());
            this.developer = dev;
        }
    }

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

    public boolean hasDeveloperId() {
        return developer != null && developer.getId() != null;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Representation of developer ID")
    public static class DeveloperId {
        @Schema(description = "Developer's unique ID", example = "123e4567-e89b-12d3-a456-426614174000")
        private UUID id;
    }
}
