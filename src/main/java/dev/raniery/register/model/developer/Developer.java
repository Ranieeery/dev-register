package dev.raniery.register.model.developer;

import dev.raniery.register.model.tasks.Tasks;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Column(name = "languages")
    @Convert(converter = LanguagesConverter.class)
    private EnumSet<Languages> languages;

    private int yearsExperience;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
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

    public void updateDeveloper(@Valid DeveloperUpdateDTO updateDTO) {
        if (updateDTO.name().isPresent()) {
            this.name = updateDTO.name().get();
        }
        if (updateDTO.languages().isPresent()) {
            this.languages = updateDTO.languages().get();
        }
        if (updateDTO.yearsExperience().isPresent()) {
            this.yearsExperience = updateDTO.yearsExperience().get();
        }
        if (updateDTO.specialization().isPresent()) {
            this.specialization = updateDTO.specialization().get();
        }
        if (updateDTO.seniority().isPresent()) {
            this.seniority = updateDTO.seniority().get();
        }
        if (updateDTO.linkedin().isPresent()) {
            this.linkedin = updateDTO.linkedin().get();
        }
        if (updateDTO.github().isPresent()) {
            this.github = updateDTO.github().get();
        }
    }
}
