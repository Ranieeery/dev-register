package dev.raniery.register.model.user;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String name,
    String email) {

    public UserDTO(Users user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }
}
