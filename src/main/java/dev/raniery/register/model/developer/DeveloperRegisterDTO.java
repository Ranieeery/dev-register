package dev.raniery.register.model.developer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.EnumSet;

@Schema(description = "DTO for registering a new developer")
public record DeveloperRegisterDTO(

    @Schema(description = "Developer's full name", example = "Mary Johnson")
    @NotBlank
    String name,

    @Schema(description = "Set of programming languages the developer knows")
    @NotNull
    EnumSet<Languages> languages,

    @Schema(description = "Years of experience as a developer", example = "5")
    @NotNull
    int yearsExperience,

    @Schema(description = "Developer's area of specialization")
    @NotNull
    Specialization specialization,

    @Schema(description = "Developer's seniority level")
    @NotNull
    Seniority seniority,

    @Schema(description = "URL of the developer's LinkedIn profile", example = "https://linkedin.com/in/maryjohnson")
    String linkedin,

    @Schema(description = "URL of the developer's GitHub profile", example = "https://github.com/maryjohnson")
    String github) {
}
