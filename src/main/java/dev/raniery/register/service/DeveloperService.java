package dev.raniery.register.service;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.DeveloperRegisterDTO;
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

    public Developer createDeveloper(DeveloperRegisterDTO developer) {
        Developer developerEntity = new Developer(developer);

        return developerRepository.save(developerEntity);
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Developer updateDeveloper(UUID id, Developer developer) {

        if (developerRepository.existsById(id)) {
            developer.setId(id);
            return developerRepository.save(developer);
        }

        return null;
    }

    public Developer findById(UUID id) {
        Optional<Developer> developer = developerRepository.findById(id);
        return developer.orElse(null);
    }

    public void deleteDeveloper(UUID id) {
        Developer developer = developerRepository.getReferenceById(id);

        developer.setActive();
        developerRepository.save(developer);
    }
}
