package dev.raniery.register.controller;

import dev.raniery.register.model.developer.Developer;
import dev.raniery.register.model.developer.DeveloperListDTO;
import dev.raniery.register.model.developer.DeveloperRegisterDTO;
import dev.raniery.register.model.developer.DeveloperUpdateDTO;
import dev.raniery.register.service.DeveloperService;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    //TODO: ResponseEntity com retornos personalizados
    @PostMapping("/create")
    @Transactional
    public Developer createDeveloper(@RequestBody @Valid DeveloperRegisterDTO developer) {
        return developerService.createDeveloper(developer);
    }

    @GetMapping("/list")
    public PagedModel<EntityModel<DeveloperListDTO>> listDeveloper(@PageableDefault(sort = {"name"}) Pageable pageable, PagedResourcesAssembler<DeveloperListDTO> assembler) {
        Page<DeveloperListDTO> developerListDTOS = developerService.findAll(pageable);

        return assembler.toModel(developerListDTOS);
    }

    @GetMapping("/list/{id}")
    public DeveloperListDTO listDeveloperById(@PathVariable UUID id) {
        return developerService.findById(id);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public DeveloperUpdateDTO updateDeveloper(@PathVariable UUID id, @RequestBody DeveloperUpdateDTO developer) {
        return developerService.updateDeveloper(id, developer);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Object> deleteDeveloper(@PathVariable UUID id) {
        developerService.deleteDeveloper(id);

        return ResponseEntity.noContent().build();
    }
}
