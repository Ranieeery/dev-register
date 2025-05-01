package dev.raniery.register.model.developer;

import dev.raniery.register.model.tasks.Tasks;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public record DeveloperListDTO(

    UUID id,
    String name,
    EnumSet<Languages> languages,
    int yearsExperience,
    Specialization specialization,
    Seniority seniority,
    String linkedin,
    String github,
    List<Tasks> tasks) {

    public DeveloperListDTO(Developer developer) {
        this(
            developer.getId(),
            developer.getName(),
            developer.getLanguages(),
            developer.getYearsExperience(),
            developer.getSpecialization(),
            developer.getSeniority(),
            developer.getLinkedin(),
            developer.getGithub(),
            developer.getTasks()
        );
    }
}
