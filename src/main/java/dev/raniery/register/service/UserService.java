package dev.raniery.register.service;

import dev.raniery.register.model.user.Users;
import dev.raniery.register.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }
}
