package org.example.repository;

import org.example.dto.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CommunityRepository extends JpaRepository<CommunityPost, Long> {
    Optional<List<CommunityPost>> findByUsername(String username);
}
