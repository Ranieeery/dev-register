package dev.raniery.register.controller;

import dev.raniery.register.model.tasks.Tasks;
import dev.raniery.register.model.tasks.TasksDTO;
import dev.raniery.register.model.tasks.TasksRegisterDTO;
import dev.raniery.register.model.tasks.TasksUpdateDTO;
import dev.raniery.register.service.TasksServive;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//TODO: Fazer TODA a lógica para a as tasks com class DTO e Mapper
@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksServive tasksServive;

    public TasksController(TasksServive tasksServive) {
        this.tasksServive = tasksServive;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Tasks> createTask(@RequestBody @Valid TasksRegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        Tasks task = tasksServive.createTask(registerDTO);

        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping("/list")
    public ResponseEntity<PagedModel<EntityModel<TasksDTO>>> listTasks(@PageableDefault(sort = {"id"}) Pageable pageable, PagedResourcesAssembler<TasksDTO> assembler) {
        Page<TasksDTO> tasksDTOS = tasksServive.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(tasksDTOS));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listTasksById(@PathVariable Long id) {

        if (tasksServive.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or deleted.");
        }

        return ResponseEntity.ok().body(tasksServive.findById(id));
    }

    //TODO: ResponseEntity return
    @Transactional
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TasksUpdateDTO updateDTO) {

        if (tasksServive.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or deleted.");
        }

        Tasks task = tasksServive.updateTask(id, updateDTO);

        return ResponseEntity.ok(task);
    }

    //TODO: ResponseEntity return
    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        return id.toString();
    }
}
