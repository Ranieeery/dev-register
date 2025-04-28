package dev.raniery.register.model.tasks;

import dev.raniery.register.model.developer.Languages;
import dev.raniery.register.model.developer.Developer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Languages language;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    public Tasks() {
    }

    public Tasks(Long id, String name, String description, Languages language, LocalDate dueDate, Developer developer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
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
}
