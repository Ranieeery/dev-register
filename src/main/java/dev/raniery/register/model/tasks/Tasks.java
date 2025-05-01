package dev.raniery.register.model.tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.raniery.register.model.developer.Languages;
import dev.raniery.register.model.developer.Developer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Languages language;

    private LocalDateTime startDate;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    @JsonIgnore
    private Developer developer;

    public Tasks() {
    }

    public Tasks(Long id, String name, String description, Languages language, LocalDateTime startDate, LocalDate dueDate, Developer developer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.developer = developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Languages getLanguage() {
        return language;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
