package dev.raniery.register.controller;

import dev.raniery.register.config.TokenService;
import dev.raniery.register.model.user.*;
import dev.raniery.register.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "API for user registration and authentication")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationManager authManager;

    public AuthController(TokenService tokenService, UserService userService, AuthenticationManager authManager) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Creates a new user in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad request - validation error"),
        @ApiResponse(responseCode = "409", description = "Email already registered")
    })
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userDto) {
        Users user = userService.save(new Users(userDto));

        return ResponseEntity
            .created(URI.create("/developer/list/" + user.getId().toString()))
            .body(new UserDTO(user));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticates a user and returns a JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful",
            content = @Content(schema = @Schema(implementation = UserTokenDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad request - validation error"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<UserTokenDTO> login(@Valid @RequestBody UserLoginDTO userDto) {
        var authToken = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());

        Authentication auth = authManager.authenticate(authToken);

        Users user = (Users) auth.getPrincipal();

        return ResponseEntity.ok(new UserTokenDTO(tokenService.generateToken(user)));
    }
}
