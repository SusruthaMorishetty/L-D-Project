package com.ust.empoweru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDate sendDate;
//    private boolean isRead;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;


}

