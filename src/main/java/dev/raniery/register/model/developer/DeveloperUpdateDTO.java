package dev.raniery.register.model.developer;

import java.util.EnumSet;

public record DeveloperUpdateDTO(
    String name,
    EnumSet<Languages> languages,
    Integer yearsExperience,
    Specialization specialization,
    Seniority seniority,
    String linkedin,
    String github) {
}
