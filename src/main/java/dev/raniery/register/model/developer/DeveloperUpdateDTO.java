package dev.raniery.register.model.developer;

import java.util.EnumSet;
import java.util.Optional;

public record DeveloperUpdateDTO(
    Optional<String> name,
    Optional<EnumSet<Languages>> languages,
    Optional<Integer> yearsExperience,
    Optional<Specialization> specialization,
    Optional<Seniority> seniority,
    Optional<String> linkedin,
    Optional<String> github) {
}
