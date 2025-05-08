package dev.raniery.register.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(

    @NotBlank
    String name,

    @Email
    @NotBlank
    String email,

    @NotBlank
    String password) {
}
