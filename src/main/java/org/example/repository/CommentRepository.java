package org.example.repository;

import org.example.dto.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByParentPostId(Long parentPostId);
}
