package com.ust.progress.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Eid;
//    private Long userId;
//    private Long courseId;
    private String status;
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;  // From Course Service

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
