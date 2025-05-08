package dev.raniery.register.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO for new user registration")
public record UserRegisterDTO(

    @Schema(description = "User's full name", example = "John Smith")
    @NotBlank
    String name,

    @Schema(description = "User's email (used for login)", example = "john.smith@email.com")
    @Email
    @NotBlank
    String email,

    @Schema(description = "User's password", example = "password123")
    @NotBlank
    String password) {
}
