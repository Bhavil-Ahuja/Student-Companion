package org.example.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.example.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Transactional
  Optional<User> deleteByUsername(String username);
}
