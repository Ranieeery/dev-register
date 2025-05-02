package dev.raniery.register.controller;

import dev.raniery.register.model.tasks.Tasks;
import dev.raniery.register.model.tasks.TasksDTO;
import dev.raniery.register.model.tasks.TasksRegisterDTO;
import dev.raniery.register.service.TasksServive;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksServive tasksServive;

    public TasksController(TasksServive tasksServive) {
        this.tasksServive = tasksServive;
    }

    //TODO: ResponseEntity return
    //TODO: DTOs personalizados
    //TODO: Fazer TODA a lógica para a as tasks com class DTO e Mapper
    @PostMapping("/create")
    @Transactional
    public Tasks createTask(@RequestBody @Valid TasksRegisterDTO registerDTO) {
        TasksRegisterDTO taskDto = tasksServive.createTask(registerDTO);

        return new Tasks(taskDto);
    }

    @PutMapping
    public String updateTask() {
        return "Task updated";
    }

    @GetMapping("/list")
    public PagedModel<EntityModel<TasksDTO>> listTasks(@PageableDefault(sort = {"name"}) Pageable pageable, PagedResourcesAssembler<TasksDTO> assembler) {
        Page<TasksDTO> tasksDTOS = tasksServive.findAll(pageable);

        return assembler.toModel(tasksDTOS);
    }

    @GetMapping("/list/{id}")
    public String listTasksById(@PathVariable String id) {
        return "Task list";
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable String id) {
        return "Task deleted";
    }
}
