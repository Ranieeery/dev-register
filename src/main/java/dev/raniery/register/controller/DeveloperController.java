package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.service.DeveloperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping
    public String createDeveloper() {
        return "Developer created";
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

    @DeleteMapping("/{id}")
    public String deleteDeveloper() {
        return "Developer deleted";
    }
}
