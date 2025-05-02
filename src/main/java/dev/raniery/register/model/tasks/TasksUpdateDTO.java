package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.Languages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksUpdateDTO {
    private Long id;
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
    private Boolean completed;
    private Priority priority;

    @JsonProperty("developer")
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
    public static class DeveloperId {
        private UUID id;
    }
}
