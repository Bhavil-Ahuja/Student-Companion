package org.example.repository;

import java.util.*;
import org.example.dto.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long> {
  Optional<List<CommunityPost>> findByUsername(String username);
}
