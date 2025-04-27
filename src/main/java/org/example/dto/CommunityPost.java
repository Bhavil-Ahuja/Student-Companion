package org.example.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "community_post")
@Getter
@Setter
public class CommunityPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private String username;

    @Column
    private String createTs;

    @Column
    private String modifyTs;

    public CommunityPost() {}
}