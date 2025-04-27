package org.example.repository;

import java.util.Optional;
import org.example.dto.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Long> {
  Optional<College> findByName(String name);
}
