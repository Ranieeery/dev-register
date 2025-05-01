package dev.raniery.register.model.developer;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import dev.raniery.register.model.tasks.Tasks;

import java.util.EnumSet;

@Entity
@Table(name = "tb_developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    private EnumSet<Languages> languages;

    private int yearsExperiente;

    private Specialization specialization;

    private Seniority seniority;
    private String linkedin;
    private String github;

    @OneToMany(mappedBy = "developer")
    private List<Tasks> tasks;

    public Developer() {
    }

    public Developer(UUID id, String name, EnumSet<Languages> languages, int yearsExperiente, Specialization specialization, Seniority seniority, String linkedin, String github, List<Tasks> tasks) {
        this.id = id;
        this.name = name;
        this.languages = languages;
        this.yearsExperiente = yearsExperiente;
        this.specialization = specialization;
        this.seniority = seniority;
        this.linkedin = linkedin;
        this.github = github;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnumSet<Languages> getLanguages() {
        return languages;
    }

    public int getYearsExperiente() {
        return yearsExperiente;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguages(EnumSet<Languages> languages) {
        this.languages = languages;
    }

    public void setYearsExperiente(int yearsExperiente) {
        this.yearsExperiente = yearsExperiente;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
}
