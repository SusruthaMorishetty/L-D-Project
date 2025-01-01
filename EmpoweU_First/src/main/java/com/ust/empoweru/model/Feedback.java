package com.ust.empoweru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity

public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float rating;
    private String comments;
    private LocalDate feedbackDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    @PrePersist
    protected void onCreate(){
        feedbackDate=LocalDate.now();
    }

}
