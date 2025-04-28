package dev.raniery.register.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @PostMapping
    public String createDeveloper() {
        return "Developer created";
    }

    @PutMapping
    public String updateDeveloper() {
        return "Developer updated";
    }

    @GetMapping("/list")
    public String listDeveloper() {
        return "List of developers";
    }

    @GetMapping("/list/{id}")
    public String listDeveloperById() {
        return "Developer by id";
    }

    @DeleteMapping("/{id}")
    public String deleteDeveloper() {
        return "Developer deleted";
    }
}
