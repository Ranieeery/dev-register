package dev.raniery.register.model.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "DTO for user data representation")
public record UserDTO(
    @Schema(description = "User's unique identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID id,

    @Schema(description = "User's name", example = "John Smith")
    String name,

    @Schema(description = "User's email", example = "john.smith@email.com")
    String email) {

    public UserDTO(Users user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }
}
