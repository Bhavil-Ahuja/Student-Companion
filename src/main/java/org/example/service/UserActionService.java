package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.College;
import org.example.dto.Comment;
import org.example.dto.CommunityPost;
import org.example.dto.User;
import org.example.helper.UserHelper;
import org.example.repository.CollegeRepository;
import org.example.repository.CommentRepository;
import org.example.repository.CommunityRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class UserActionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserHelper userHelper;

    public HttpStatus updateUserData(String username, User user) {
        Optional<User> currentUser = userRepository.findByUsername(username);
        if (currentUser.isPresent()) {
            userHelper.updateUserFields(currentUser.get(), user);
            User savedUser = userRepository.save(currentUser.get());
            if (savedUser.getId() != null) {
                return HttpStatus.ACCEPTED;
            } else {
                return HttpStatus.NOT_MODIFIED;
            }
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public long getCollegeId(String name) {
        Optional<College> college = collegeRepository.findByName(name);
        return college.isPresent() ? college.get().getId() : -1;
    }

    public HttpStatus postToCommunity(CommunityPost communityPost) {
        userHelper.setMandatoryPostAttributes(communityPost);
        CommunityPost communityPostSaved = communityRepository.save(communityPost);
        if (communityPostSaved.getId() != null) {
            return HttpStatus.ACCEPTED;
        } else {
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public HttpStatus commentOnPost(Comment comment) {
        Optional<CommunityPost> communityPostOpt = communityRepository.findById(comment.getParentPost().getId());
        if (communityPostOpt.isPresent()) {
            userHelper.setMandatoryCommentAttributes(comment);
            comment.setParentPost(communityPostOpt.get());
            log.debug("Saving comment: {}", comment);
            Comment savedComment = commentRepository.save(comment);
            log.debug("Saved comment with ID: {}", savedComment.getId());
            if (savedComment.getId() != null) {
                return HttpStatus.ACCEPTED;
            } else {
                return HttpStatus.NOT_MODIFIED;
            }
        } else {
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public List<CommunityPost> getAllPosts() {
        Optional<List<CommunityPost>> posts = communityRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return posts.orElse(null);
    }

    public List<Comment> getAllCommentsOnAPost(Long postId) {
        Optional<List<Comment>> comments = commentRepository.findByParentPostId(postId);
        return comments.orElse(null);
    }

}
