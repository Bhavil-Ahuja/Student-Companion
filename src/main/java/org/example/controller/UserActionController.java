package org.example.controller;

import org.example.dto.CommunityPost;
import org.example.dto.User;
import org.example.repository.UserRepository;
import org.example.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class UserActionController {

  @Autowired
  UserActionService userActionService;

  @GetMapping("/dashboard")
  public String dashboard() {
    return "Welcome to the Student Dashboard, "
        + SecurityContextHolder.getContext().getAuthentication().getName();
  }

  @PostMapping("/setUserData")
  public ResponseEntity<String> setUserData(@RequestBody User user) {
    HttpStatus result = userActionService.updateUserData(SecurityContextHolder.getContext().getAuthentication().getName(), user);
    if (result.isError()) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Invalid user");
    } else {
      return ResponseEntity.ok("Update Successful");
    }
  }

  @GetMapping("/getCollegeId")
  public ResponseEntity<Long> getCollegeId(@RequestParam String collegeName) {
    return ResponseEntity.ok(userActionService.getCollegeId(collegeName));
  }
}
