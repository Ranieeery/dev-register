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
public class TasksDTO {
    private Long id;
    private String name;
    private String description;
    private Languages language;
    private LocalDateTime startDate;
    private LocalDate dueDate;

    @JsonIgnore
    private Developer developer;
    
    @JsonProperty("developer")
    public DeveloperId getDeveloperId() {
        return new DeveloperId(developer != null ? developer.getId() : null);
    }
    
    private Status status;
    private LocalDate createdAt;
    private LocalDate finishDate;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeveloperId {
        private UUID id;
    }
}
