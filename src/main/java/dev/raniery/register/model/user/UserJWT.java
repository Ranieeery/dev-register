package dev.raniery.register.model.user;

import java.util.UUID;

public record UserJWT(UUID id, String name, String email) {
}
