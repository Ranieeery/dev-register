package dev.raniery.register.controller;

import dev.raniery.register.model.tasks.Tasks;
import dev.raniery.register.model.tasks.TasksDTO;
import dev.raniery.register.model.tasks.TasksRegisterDTO;
import dev.raniery.register.model.tasks.TasksUpdateDTO;
import dev.raniery.register.service.TasksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "API for developer task management")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @Transactional
    @PostMapping("/create")
    @Operation(summary = "Create a new task", description = "Creates a new task and stores it in the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully",
            content = @Content(schema = @Schema(implementation = Tasks.class))),
        @ApiResponse(responseCode = "400", description = "Bad request - validation error")
    })
    public ResponseEntity<Tasks> createTask(
        @Parameter(description = "Task data to be created", required = true)
        @RequestBody @Valid TasksRegisterDTO registerDTO,
        UriComponentsBuilder uriBuilder) {
        Tasks task = tasksService.createTask(registerDTO);

        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping("/list")
    @Operation(summary = "List all tasks", description = "Returns a paginated list of all active tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tasks listed successfully")
    })
    public ResponseEntity<PagedModel<EntityModel<TasksDTO>>> listTasks(
        @Parameter(description = "Pagination parameters")
        @PageableDefault(sort = {"id"}) Pageable pageable,
        PagedResourcesAssembler<TasksDTO> assembler) {
        Page<TasksDTO> tasksDTOS = tasksService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(tasksDTOS));
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get task by ID", description = "Returns details of a specific task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task found successfully",
            content = @Content(schema = @Schema(implementation = TasksDTO.class))),
        @ApiResponse(responseCode = "404", description = "Task not found or deleted")
    })
    public ResponseEntity<Object> listTasksById(
        @Parameter(description = "Task ID", required = true, example = "1")
        @PathVariable Long id) {

        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id.toString() + " not found or deleted.");
        }

        return ResponseEntity.ok().body(tasksService.findById(id));
    }

    @Transactional
    @PatchMapping("/update/{id}")
    @Operation(summary = "Update a task", description = "Updates a specific task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task updated successfully",
            content = @Content(schema = @Schema(implementation = TasksDTO.class))),
        @ApiResponse(responseCode = "404", description = "Task not found or deleted")
    })
    public ResponseEntity<?> updateTask(
        @Parameter(description = "Task ID to be updated", required = true, example = "1")
        @PathVariable Long id,
        @Parameter(description = "Task data to be updated", required = true)
        @RequestBody TasksUpdateDTO updateDTO) {

        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id + " not found or deleted.");
        }

        return ResponseEntity.ok(tasksService.updateTask(id, updateDTO));
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete a task", description = "Marks a task as deleted by its ID (logical deletion)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found or already deleted")
    })
    public ResponseEntity<?> deleteTask(
        @Parameter(description = "Task ID to be deleted", required = true, example = "1")
        @PathVariable Long id) {
        if (tasksService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id + " not found or deleted.");
        }

        tasksService.toggleActiveTask(id);

        return ResponseEntity.ok("Task " + id + " deleted successfully!");
    }

    @Transactional
    @PatchMapping("/delete/{id}/undo")
    @Operation(summary = "Restore a deleted task", description = "Restores a previously deleted task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task restored successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found or not deleted")
    })
    public ResponseEntity<?> undeleteTask(
        @Parameter(description = "Task ID to be restored", required = true, example = "1")
        @PathVariable Long id) {

        if (tasksService.findDeletedById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task " + id + " not found or not deleted.");
        }

        tasksService.toggleActiveTask(id);

        return ResponseEntity.ok("Task " + id + " restored successfully!");
    }
}
