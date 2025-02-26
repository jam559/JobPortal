package com.jobportal.jobportal_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.service.JobPostActivityService;
import com.jobportal.jobportal_demo.service.UsersService;

@Controller
public class JobSeekerApplyController {
    
    @Autowired
    private JobPostActivityService jobPostActivityService;

    @Autowired
    private UsersService usersService;

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "job-details";
    }
}
