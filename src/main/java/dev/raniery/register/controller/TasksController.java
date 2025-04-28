package dev.raniery.register.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @PostMapping
    public String createTask() {
        return "Task added";
    }

    @PutMapping
    public String updateTask() {
        return "Task updated";
    }

    @GetMapping("/list")
    public String listTasks() {
        return "Task list";
    }

    @GetMapping("/list/{id}")
    public String listTasksById() {
        return "Task list";
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById() {
        return "Task deleted";
    }
}
