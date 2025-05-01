package dev.raniery.register.model.developer;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import dev.raniery.register.model.tasks.Tasks;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    //TODO: Adicionar Enums no banco para impedir dependência
    private EnumSet<Languages> languages;

    private int yearsExperience;

    //TODO: Adicionar Enums no banco para impedir dependência
    private Specialization specialization;

    private Seniority seniority;
    private String linkedin;
    private String github;

    private boolean active;

    @OneToMany(mappedBy = "developer")
    private List<Tasks> tasks;

    public Developer(DeveloperRegisterDTO registerDTO) {
        this.name = registerDTO.name();
        this.languages = registerDTO.languages();
        this.yearsExperience = registerDTO.yearsExperience();
        this.specialization = registerDTO.specialization();
        this.seniority = registerDTO.seniority();
        this.linkedin = registerDTO.linkedin();
        this.github = registerDTO.github();
        this.active = true;
    }

    public void setActive() {
        this.active = !active;
    }

    public void updateDeveloper(@Valid DeveloperUpdateDTO dto) {
        if (dto.name().isPresent()) {
            this.name = dto.name().get();
        }
        if (dto.languages().isPresent()) {
            this.languages = dto.languages().get();
        }
        if (dto.yearsExperience().isPresent()) {
            this.yearsExperience = dto.yearsExperience().get();
        }
        if (dto.specialization().isPresent()) {
            this.specialization = dto.specialization().get();
        }
        if (dto.seniority().isPresent()) {
            this.seniority = dto.seniority().get();
        }
        if (dto.linkedin().isPresent()) {
            this.linkedin = dto.linkedin().get();
        }
        if (dto.github().isPresent()) {
            this.github = dto.github().get();
        }
    }
}
