package com.ust.userservice.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private  Long id;

    private String name;

    private  String email;

    private String role;

    private String password;
}
