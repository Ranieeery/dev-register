package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.service.DeveloperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    //TODO: Classes records para tratar requisições
    //TODO: Records para NotNull
    //TODO: ResponseEntity com retornos personalizados
    @PostMapping("/create")
    public Developer createDeveloper(@RequestBody Developer developer) {
        return developerService.createDeveloper(developer);
    }

    @PutMapping("/update/{id}")
    public Developer updateDeveloper(@PathVariable UUID id, @RequestBody Developer developer) {
        return developerService.updateDeveloper(id, developer);
    }

    @GetMapping("/list")
    public List<Developer> listDeveloper() {
        return developerService.findAll();
    }

    @GetMapping("/list/{id}")
    public Developer listDeveloperById(@PathVariable UUID id) {
        return developerService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable UUID id) {
        developerService.deleteDeveloper(id);

        return ResponseEntity.noContent().build();
    }
}
