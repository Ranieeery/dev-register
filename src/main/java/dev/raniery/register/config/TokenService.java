package dev.raniery.register.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.raniery.register.model.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${register.security.secret}")
    private String secret;

    public String generateToken(Users user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getEmail())
            .withClaim("userId", user.getId().toString())
            .withClaim("name", user.getName())
            .withExpiresAt(expiresAt())
            .withIssuedAt(Instant.now())
            .withIssuer("Dev tasks auth API")
            .sign(algorithm);
    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
