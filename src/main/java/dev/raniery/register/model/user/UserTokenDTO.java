package dev.raniery.register.model.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for token response after authentication")
public record UserTokenDTO(
    @Schema(description = "JWT token generated for authentication",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huLnNtaXRoQGVtYWlsLmNvbSJ9.abc123")
    String token) {
}
