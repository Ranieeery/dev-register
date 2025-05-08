package dev.raniery.register.controller;

import dev.raniery.register.config.TokenService;
import dev.raniery.register.model.user.*;
import dev.raniery.register.service.UserService;
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
//TODO: Token service
//TODO: Login
//TODO: HandleExceptions
//TODO: Tokens de login
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
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserRegisterDTO userDto) {
        Users user = new Users(userDto);
        userService.save(user);

        return ResponseEntity
            .created(URI.create("/developer/list/" + user.getId().toString()))
            .body(new UserDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@Valid @RequestBody UserLoginDTO userDto) {
        var authToken = new UsernamePasswordAuthenticationToken(userDto.email(), userDto.password());

        Authentication auth = authManager.authenticate(authToken);

        Users user = (Users) auth.getPrincipal();

        return ResponseEntity.ok(new UserTokenDTO(tokenService.generateToken(user)));
    }


}
