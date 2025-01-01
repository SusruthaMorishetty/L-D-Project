package com.ust.progress.model;


import com.ust.progress.dto.Course;
import com.ust.progress.dto.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;
    private Long userId;
    private Long courseId;
    private String status;

}
