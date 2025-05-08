package dev.raniery.register.model.developer;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.EnumSet;
import java.util.Optional;

@Schema(description = "DTO for updating developer data")
public record DeveloperUpdateDTO(
    @Schema(description = "Developer's name (optional)", example = "Mary Johnson Smith")
    Optional<String> name,

    @Schema(description = "Set of programming languages (optional)")
    Optional<EnumSet<Languages>> languages,

    @Schema(description = "Years of experience (optional)", example = "6")
    Optional<Integer> yearsExperience,

    @Schema(description = "Area of specialization (optional)")
    Optional<Specialization> specialization,

    @Schema(description = "Seniority level (optional)")
    Optional<Seniority> seniority,

    @Schema(description = "LinkedIn profile URL (optional)", example = "https://linkedin.com/in/maryjohnsonsmith")
    Optional<String> linkedin,

    @Schema(description = "GitHub profile URL (optional)", example = "https://github.com/maryjohnson")
    Optional<String> github) {
}
