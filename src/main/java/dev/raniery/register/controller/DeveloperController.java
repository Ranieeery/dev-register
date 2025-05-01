package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.service.DeveloperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping("/create")
    public Developer createDeveloper(@RequestBody Developer developer) {
        return developerService.createDeveloper(developer);
    }

    @PutMapping
    public String updateDeveloper() {
        return "Developer updated";
    }

    @GetMapping("/list")
    public List<Developer> listDeveloper() {
        return developerService.findAll();
    }

    @GetMapping("/list/{id}")
    public Developer listDeveloperById(@PathVariable String id) {
        return developerService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable String id) {
        developerService.deleteDeveloper(id);

        return ResponseEntity.noContent().build();
    }
}
