package org.example.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.example.auth.JwtUtil;
import org.example.dto.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
  @Autowired private JwtUtil jwtUtil;
  @Autowired UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestParam String username, @RequestParam String password, HttpSession session) {
    // Dummy user validation
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent() && user.get().getPassword().equals(password)) {
      String token = jwtUtil.generateToken(username);
      session.setAttribute("JWT_TOKEN", token);
      return ResponseEntity.ok("Login successful");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logoutPost(HttpSession session) {
    session.invalidate();
    return ResponseEntity.ok("Logged out successfully");
  }

  // New registration endpoint
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestParam String username, @RequestParam String password) {
    if (userRepository.findByUsername(username).isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
    }

    // Save new user in database
    User user = new User(username, password);
    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> removeUser(HttpSession httpSession) {
    httpSession.invalidate();
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> deletedUser = userRepository.deleteByUsername(username);
    if (deletedUser.isPresent()) {
      return ResponseEntity.ok("User deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User couldn't be deleted");
    }
  }
}
