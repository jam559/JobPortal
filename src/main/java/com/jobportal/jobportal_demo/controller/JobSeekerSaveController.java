package com.jobportal.jobportal_demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.entity.JobSeekerProfile;
import com.jobportal.jobportal_demo.entity.JobSeekerSave;
import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.service.JobPostActivityService;
import com.jobportal.jobportal_demo.service.JobSeekerProfileService;
import com.jobportal.jobportal_demo.service.UsersService;

@Controller
public class JobSeekerSaveController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JobSeekerProfileService jobSeekerProfileService;

    @Autowired
    private JobPostActivityService jobPostActivityService;

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, JobSeekerSave jobSeekerSave) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = usersService.getUsersByEmail(currentUsername);
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
            if (seekerProfile.isPresent() && jobPostActivity != null) {
                jobSeekerSave.setJob(jobPostActivity);
                jobSeekerSave.setUserId(seekerProfile.get());
            } else {
                throw new RuntimeException("User not found");
            }
            // jobSeekerSaveService.addNew(jobSeekerSave);
        }
        return "redirect:/dashboard/";
    }

}
