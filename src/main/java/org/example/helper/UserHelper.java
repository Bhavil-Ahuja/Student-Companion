package org.example.helper;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Comment;
import org.example.dto.CommunityPost;
import org.example.dto.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class UserHelper {

    public void updateUserFields(User existingUser, User newUserDetails) {
        existingUser.setBatch(newUserDetails.getBatch());
        existingUser.setName(newUserDetails.getName());
        existingUser.setEmail(newUserDetails.getEmail());
        existingUser.setRole(newUserDetails.getRole());
        existingUser.setCollegeId(newUserDetails.getCollegeId());
        existingUser.setRollNo(newUserDetails.getRollNo());
        existingUser.setYear(newUserDetails.getYear());
        existingUser.setSemester(newUserDetails.getSemester());
        existingUser.setBranch(newUserDetails.getBranch());
        String currentDateTime = dateToString(new Date());
        if (existingUser.getCreateTs() == null) {
            existingUser.setCreateTs(currentDateTime);
        }
        existingUser.setModifyTs(currentDateTime);
    }

    public void setMandatoryPostAttributes(CommunityPost communityPost) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        communityPost.setUsername(username);
        String currentDateTime = dateToString(new Date());
        communityPost.setCreateTs(currentDateTime);
        communityPost.setModifyTs(currentDateTime);
    }

    public void setMandatoryCommentAttributes(Comment comment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        comment.setUsername(username);
        String currentDateTime = dateToString(new Date());
        comment.setCreateTs(currentDateTime);
        comment.setModifyTs(currentDateTime);
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return formatter.format(date);
    }

}
