package org.example.repository;

import java.util.*;
import org.example.dto.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  Optional<List<Comment>> findByParentPostId(Long parentPostId);
}
