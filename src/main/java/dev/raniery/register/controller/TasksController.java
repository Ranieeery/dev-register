package dev.raniery.register.controller;

import dev.raniery.register.service.TasksServive;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksServive tasksServive;

    public TasksController(TasksServive tasksServive) {
        this.tasksServive = tasksServive;
    }

    //TODO: Fazer TODA a lógica para a as tasks com class DTO e Mapper
    @PostMapping("/create")
    @Transactional
    public String createTask() {
        return "tasksServive.createTask();";
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
