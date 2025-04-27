package org.example.dto;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "parent_post_id", nullable = false)
  private CommunityPost parentPost;

  @Column(nullable = false)
  private String body;

  private String username;

  @Column private String createTs;

  @Column private String modifyTs;

  public Comment() {}
}
