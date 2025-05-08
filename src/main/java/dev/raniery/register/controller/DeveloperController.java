package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.DeveloperListDTO;
import dev.raniery.register.model.developer.DeveloperRegisterDTO;
import dev.raniery.register.model.developer.DeveloperUpdateDTO;
import dev.raniery.register.service.DeveloperService;
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
import java.util.UUID;

@RestController
@RequestMapping("/developer")
@Tag(name = "Developers", description = "API for developer management")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Transactional
    @PostMapping("/create")
    @Operation(summary = "Create a new developer", description = "Creates a new developer and stores it in the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Developer created successfully",
            content = @Content(schema = @Schema(implementation = DeveloperListDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad request - validation error")
    })
    public ResponseEntity<DeveloperListDTO> createDeveloper(
        @Parameter(description = "Developer data to be created", required = true)
        @RequestBody @Valid DeveloperRegisterDTO developerDto,
        UriComponentsBuilder uriBuilder) {
        Developer developer = developerService.createDeveloper(developerDto);

        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(developer.getId()).toUri();

        return ResponseEntity.created(uri).body(new DeveloperListDTO(developer));
    }

    @GetMapping("/list")
    @Operation(summary = "List all developers", description = "Returns a paginated list of all active developers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developers listed successfully")
    })
    public ResponseEntity<PagedModel<EntityModel<DeveloperListDTO>>> listDeveloper(
        @Parameter(description = "Pagination parameters")
        @PageableDefault(sort = {"name"}) Pageable pageable,
        PagedResourcesAssembler<DeveloperListDTO> assembler) {
        Page<DeveloperListDTO> developerListDTOS = developerService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(developerListDTOS));
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get developer by ID", description = "Returns details of a specific developer by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developer found successfully",
            content = @Content(schema = @Schema(implementation = DeveloperListDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found or deleted")
    })
    public ResponseEntity<Object> listDeveloperById(
        @Parameter(description = "Developer ID", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
        @PathVariable UUID id) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id + " not found or deleted.");
        }

        return ResponseEntity.ok(developerService.findById(id));
    }

    @Transactional
    @PatchMapping("/update/{id}")
    @Operation(summary = "Update a developer", description = "Updates a specific developer by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developer updated successfully",
            content = @Content(schema = @Schema(implementation = DeveloperUpdateDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found or deleted")
    })
    public ResponseEntity<?> updateDeveloper(
        @Parameter(description = "Developer ID to be updated", required = true)
        @PathVariable UUID id,
        @Parameter(description = "Developer data to be updated", required = true)
        @RequestBody @Valid DeveloperUpdateDTO updateDTO) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id + " not found or deleted.");
        }

        return ResponseEntity.ok(developerService.updateDeveloper(id, updateDTO));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a developer", description = "Marks a developer as deleted by its ID (logical deletion)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developer deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Developer not found or already deleted")
    })
    public ResponseEntity<?> deleteDeveloper(
        @Parameter(description = "Developer ID to be deleted", required = true)
        @PathVariable UUID id) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id + " not found or deleted.");
        }

        developerService.toggleActiveDeveloper(id);

        return ResponseEntity.ok("Developer " + id + " deleted successfully!");
    }

    @Transactional
    @PatchMapping("/delete/{id}/undo")
    @Operation(summary = "Restore a deleted developer", description = "Restores a previously deleted developer by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Developer restored successfully"),
        @ApiResponse(responseCode = "404", description = "Developer not found or not deleted")
    })
    public ResponseEntity<?> undeleteDeveloper(
        @Parameter(description = "Developer ID to be restored", required = true)
        @PathVariable UUID id) {

        if (developerService.findDeletedById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id + " not found or not deleted.");
        }

        developerService.toggleActiveDeveloper(id);

        return ResponseEntity.ok("Developer " + id + " restored successfully!");
    }
}
