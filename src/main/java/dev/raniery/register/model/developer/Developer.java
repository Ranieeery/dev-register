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

    //TODO: Construtor personalizado
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

    //TODO: Inverter lógica para alterar valor
    public void setActive() {
        this.active = !active;
    }

    public void updateDeveloper(@Valid DeveloperUpdateDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.languages() != null) {
            this.languages = dto.languages();
        }
        if (dto.yearsExperience() != null) {
            this.yearsExperience = dto.yearsExperience();
        }
        if (dto.specialization() != null) {
            this.specialization = dto.specialization();
        }
        if (dto.seniority() != null) {
            this.seniority = dto.seniority();
        }
        if (dto.linkedin() != null) {
            this.linkedin = dto.linkedin();
        }
        if (dto.github() != null) {
            this.github = dto.github();
        }
    }
}
