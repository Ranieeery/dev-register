package dev.raniery.register.service;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.DeveloperListDTO;
import dev.raniery.register.model.developer.DeveloperRegisterDTO;
import dev.raniery.register.model.developer.DeveloperUpdateDTO;
import dev.raniery.register.repository.DeveloperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public Page<DeveloperListDTO> findAll(Pageable pageable) {
        return developerRepository.findAllByActiveTrue(pageable).map(DeveloperListDTO::new);
    }

    public DeveloperListDTO findById(UUID id) {
        Optional<DeveloperListDTO> developer = developerRepository.findByIdAndActiveTrue(id).map(DeveloperListDTO::new);

        return developer.orElse(null);
    }

    public DeveloperListDTO findDeletedById(UUID id) {
        Optional<DeveloperListDTO> developer = developerRepository.findByIdAndActiveFalse(id).map(DeveloperListDTO::new);

        return developer.orElse(null);
    }

    public DeveloperUpdateDTO updateDeveloper(UUID id, DeveloperUpdateDTO developerUpdateDTO) {
        Optional<Developer> optionalDeveloper = developerRepository.findByIdAndActiveTrue(id);

        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            developer.updateDeveloper(developerUpdateDTO);

            Developer updatedDeveloper = developerRepository.save(developer);

            return new DeveloperUpdateDTO(
                Optional.ofNullable(updatedDeveloper.getName()),
                Optional.ofNullable(updatedDeveloper.getLanguages()),
                Optional.of(updatedDeveloper.getYearsExperience()),
                Optional.ofNullable(updatedDeveloper.getSpecialization()),
                Optional.ofNullable(updatedDeveloper.getSeniority()),
                Optional.ofNullable(updatedDeveloper.getLinkedin()),
                Optional.ofNullable(updatedDeveloper.getGithub())
            );
        }

        return null;
    }

    public void toggleActiveDeveloper(UUID id) {
        Developer developer = developerRepository.getReferenceById(id);

        developer.setActive();
        developerRepository.save(developer);
    }
}
