package dev.raniery.register.model.developer;

import dev.raniery.register.model.tasks.Tasks;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

@Schema(description = "DTO for listing developers with their complete information")
public record DeveloperListDTO(

    @Schema(description = "Developer's unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id,

    @Schema(description = "Developer's name", example = "Mary Johnson")
    String name,

    @Schema(description = "Programming languages known by the developer")
    EnumSet<Languages> languages,

    @Schema(description = "Years of experience", example = "5")
    int yearsExperience,

    @Schema(description = "Developer's main specialization")
    Specialization specialization,

    @Schema(description = "Developer's seniority level")
    Seniority seniority,

    @Schema(description = "LinkedIn profile URL", example = "https://linkedin.com/in/maryjohnson")
    String linkedin,

    @Schema(description = "GitHub profile URL", example = "https://github.com/maryjohnson")
    String github,

    @Schema(description = "List of tasks assigned to the developer")
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
