package dev.raniery.register.controller;

import dev.raniery.register.model.tasks.Tasks;
import dev.raniery.register.model.tasks.TasksDTO;
import dev.raniery.register.model.tasks.TasksRegisterDTO;
import dev.raniery.register.model.tasks.TasksUpdateDTO;
import dev.raniery.register.service.TasksService;
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

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<Tasks> createTask(@RequestBody @Valid TasksRegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        Tasks task = tasksService.createTask(registerDTO);

        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping("/list")
    public ResponseEntity<PagedModel<EntityModel<TasksDTO>>> listTasks(@PageableDefault(sort = {"id"}) Pageable pageable, PagedResourcesAssembler<TasksDTO> assembler) {
        Page<TasksDTO> tasksDTOS = tasksService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(tasksDTOS));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listTasksById(@PathVariable Long id) {

        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or deleted.");
        }

        return ResponseEntity.ok().body(tasksService.findById(id));
    }

    @Transactional
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TasksUpdateDTO updateDTO) {

        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or deleted.");
        }

        return ResponseEntity.ok(tasksService.updateTask(id, updateDTO));
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " Not found or deleted. :(");
        }

        tasksService.deleteTask(id);

        return ResponseEntity.ok("Task " + id.toString() + " deleted with success!");
    }

    @Transactional
    @PatchMapping("/delete/{id}/undo")
    public ResponseEntity<Object> undeleteTask(@PathVariable Long id) {

        if (tasksService.findDeletedById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or not deleted.");
        }

        tasksService.deleteTask(id);

        return ResponseEntity.ok("Task " + id.toString() + " restored with success!");
    }
}
