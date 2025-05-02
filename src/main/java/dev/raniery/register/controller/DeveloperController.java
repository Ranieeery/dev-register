package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.DeveloperListDTO;
import dev.raniery.register.model.developer.DeveloperRegisterDTO;
import dev.raniery.register.model.developer.DeveloperUpdateDTO;
import dev.raniery.register.service.DeveloperService;
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
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<DeveloperListDTO> createDeveloper(@RequestBody @Valid DeveloperRegisterDTO developerDto, UriComponentsBuilder uriBuilder) {
        Developer developer = developerService.createDeveloper(developerDto);

        URI uri = uriBuilder.path("/list/{id}").buildAndExpand(developer.getId()).toUri();

        return ResponseEntity.created(uri).body(new DeveloperListDTO(developer));
    }

    @GetMapping("/list")
    public ResponseEntity<PagedModel<EntityModel<DeveloperListDTO>>> listDeveloper(@PageableDefault(sort = {"name"}) Pageable pageable, PagedResourcesAssembler<DeveloperListDTO> assembler) {
        Page<DeveloperListDTO> developerListDTOS = developerService.findAll(pageable);

        return ResponseEntity.ok(assembler.toModel(developerListDTOS));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listDeveloperById(@PathVariable UUID id) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id.toString() + " not found or deleted.");
        }

        return ResponseEntity.ok(developerService.findById(id));
    }

    @Transactional
    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateDeveloper(@PathVariable UUID id, @RequestBody DeveloperUpdateDTO developer) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id.toString() + " not found or not deleted.");
        }

        return ResponseEntity.ok(developerService.updateDeveloper(id, developer));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable UUID id) {

        if (developerService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id.toString() + " Not found or deleted. :(");
        }

        developerService.deleteDeveloper(id);

        return ResponseEntity.ok("Developer " + id.toString() + " deleted with success!");
    }

    @Transactional
    @PatchMapping("/delete/{id}/undo")
    public ResponseEntity<Object> undeleteDeveloper(@PathVariable UUID id) {

        if (developerService.findDeletedById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer " + id.toString() + " not found or not deleted.");
        }

        developerService.deleteDeveloper(id);

        return ResponseEntity.ok("Developer " + id.toString() + " restored with success!");
    }
}
