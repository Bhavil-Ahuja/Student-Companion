package org.example.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String role;

    @Column
    private Long collegeId;

    @Column
    private String rollNo;

    @Column
    private Long year;

    @Column
    private Long semester;

    @Column
    private String batch;

    @Column
    private String branch;

    @Column
    private String createTs;

    @Column
    private String modifyTs;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}