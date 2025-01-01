package com.ust.progress.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
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

    @JsonIgnore
    @OneToMany(mappedBy = "trainingProgram", cascade = CascadeType.ALL)
    private List<Course> courses;

    @ElementCollection
    @JsonIgnore
    private Set<Long>trainees = new HashSet<>();
}
