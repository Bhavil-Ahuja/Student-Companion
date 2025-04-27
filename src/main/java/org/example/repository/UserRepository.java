package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Transactional
    Optional<User> deleteByUsername(String username);
}
