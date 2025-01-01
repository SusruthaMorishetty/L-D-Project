package com.ust.empoweru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate scheduledDate;
    private String mode;
    private String locationOrLink;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "schedule_attendees",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Employee> attendees;


}
