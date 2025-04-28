package dev.raniery.register.model.tasks;

import dev.raniery.register.model.developer.Languages;
import dev.raniery.register.model.developer.Developer;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    private Developer developer;

    
}
