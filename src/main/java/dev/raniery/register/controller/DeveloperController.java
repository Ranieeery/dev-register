package dev.raniery.register.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @PostMapping
    public String createDeveloper() {
        return "Developer created";
    }

    @GetMapping("/all")
    public String listDeveloper() {
        return "List of developers";
    }

    @GetMapping("/all/{id}")
    public String listDeveloperById() {
        return "Developer by id";
    }

    @PutMapping
    public String updateDeveloper() {
        return "Developer updated";
    }

    @DeleteMapping("/{id}")
    public String deleteDeveloper() {
        return "Developer deleted";
    }
}
