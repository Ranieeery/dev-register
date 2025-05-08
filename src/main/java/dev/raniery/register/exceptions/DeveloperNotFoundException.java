package dev.raniery.register.exceptions;

import java.util.UUID;

public class DeveloperNotFoundException extends RuntimeException {

    public DeveloperNotFoundException(String message) {
        super(message);
    }

    public static DeveloperNotFoundException forDeveloperId(UUID id) {
        return new DeveloperNotFoundException("Developer with ID " + id + " not found");
    }
}
