package dev.raniery.register.service;

import dev.raniery.register.exceptions.EmailAlreadyExistsException;
import dev.raniery.register.model.user.Users;
import dev.raniery.register.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users save(Users users) {

        if (userRepository.existsByEmail(users.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        String password = users.getPassword();
        users.setPassword(passwordEncoder.encode(password));

        return userRepository.save(users);
    }
}
