package dev.raniery.register.model.developer;

import jakarta.validation.constraints.NotBlank;

import java.util.EnumSet;

public record DeveloperRegisterDTO(

    @NotBlank
    String name,

    @NotBlank
    EnumSet<Languages> languages,

    @NotBlank
    int yearsExperience,

    @NotBlank
    Specialization specialization,

    @NotBlank
    Seniority seniority,
    
    String linkedin,

    String github) {
}
