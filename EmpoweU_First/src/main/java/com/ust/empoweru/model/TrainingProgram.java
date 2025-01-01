package com.ust.empoweru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.Set;

@Data
@Entity
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String targetRole;


    @JsonIgnore
    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<Course> courses;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "employee_training_program",
            joinColumns = @JoinColumn(name = "training_program_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;
}
