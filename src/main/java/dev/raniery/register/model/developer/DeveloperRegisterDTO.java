package dev.raniery.register.model.developer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.EnumSet;

public record DeveloperRegisterDTO(

    @NotBlank
    String name,

    @NotNull
    EnumSet<Languages> languages,

    @NotNull
    int yearsExperience,

    @NotNull
    Specialization specialization,

    @NotNull
    Seniority seniority,
    
    String linkedin,
    String github) {
}
