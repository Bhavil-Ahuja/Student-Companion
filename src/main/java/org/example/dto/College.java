package org.example.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "college")
@Getter
@Setter
public class College {
    @Column(nullable = false)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public College() {}

    public College(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
