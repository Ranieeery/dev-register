package dev.raniery.register.model;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.EnumSet;

@Entity
@Table(name = "tb_developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private EnumSet<Languages> languagens;
    private int yearsExperiente;
    private Especialization especialization;
    private Seniority seniority;
    private String linkedin;
    private String github;

    public Developer() {
    }

    public Developer(String name, EnumSet<Languages> languagens, int yearsExperiente, Especialization especialization, Seniority seniority, String linkedin, String github) {
        this.name = name;
        this.languagens = languagens;
        this.yearsExperiente = yearsExperiente;
        this.especialization = especialization;
        this.seniority = seniority;
        this.linkedin = linkedin;
        this.github = github;
    }
}
