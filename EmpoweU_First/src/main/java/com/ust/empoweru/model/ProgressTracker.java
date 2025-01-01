package com.ust.empoweru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class ProgressTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate completionDate;
    private Status status;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
