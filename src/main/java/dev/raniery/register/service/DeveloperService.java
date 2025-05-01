package dev.raniery.register.service;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer createDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Developer findById(String id) {
        UUID uuid = UUID.fromString(id);

        Optional<Developer> developer = developerRepository.findById(uuid);
        return developer.orElse(null);
    }
}
