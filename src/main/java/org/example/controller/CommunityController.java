package org.example.controller;

import org.example.dto.Comment;
import org.example.dto.CommunityPost;
import org.example.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    UserActionService userActionService;

    @PostMapping("/postToCommunity")
    public ResponseEntity<String> postToCommunity(@RequestBody CommunityPost communityPost) {
        HttpStatus result = userActionService.postToCommunity(communityPost);
        if (result.isError()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not Posted!");
        } else {
            return ResponseEntity.ok("Posted!");
        }
    }

    @PostMapping("/commentOnPost")
    public ResponseEntity<String> commentOnPost(@RequestBody Comment comment) {
        HttpStatus result = userActionService.commentOnPost(comment);
        if (result.isError()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Comment Not Posted!");
        } else {
            return ResponseEntity.ok("Comment Posted!");
        }
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<CommunityPost>> getAllPosts() {
        return ResponseEntity.ok(userActionService.getAllPosts());
    }

    @GetMapping("/getAllCommentsOnAPost")
    public ResponseEntity<List<Comment>> getAllCommentsOnAPost(@RequestParam Long postId) {
        return ResponseEntity.ok(userActionService.getAllCommentsOnAPost(postId));
    }
}
