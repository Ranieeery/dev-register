package dev.raniery.register.controller;

import dev.raniery.register.model.user.UserDTO;
import dev.raniery.register.model.user.UserRegisterDTO;
import dev.raniery.register.model.user.Users;
import dev.raniery.register.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//TODO: Token service
//TODO: Login
//TODO: HandleExceptions
//TODO: Tokens de login
public class AuthController {

    //    private final TokenService tokenService;
    private final UserService userService;

    public AuthController(/*TokenService tokenService,*/ UserService userService) {
//        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDTO register(@Valid @RequestBody UserRegisterDTO user) {
        Users users = new Users(user);
        userService.save(users);

        return new UserDTO(users);
    }


}
