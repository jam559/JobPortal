package com.jobportal.jobportal_demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.JobSeekerProfile;
import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.repository.JobSeekerProfileRepository;
import com.jobportal.jobportal_demo.repository.UsersRepository;

@Service
public class JobSeekerProfileService {

    @Autowired
    private JobSeekerProfileRepository jobSeekerProfileRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users user = null;
            try {
                user = usersRepository.getUsersByEmail(currentUsername);
            } catch (UsernameNotFoundException e) {
                System.out.println("User not found.");
            }
            Optional<JobSeekerProfile> seekerProfile = getOne(user.getUserId());
            return seekerProfile.orElse(null);
        } else
            return null;
    }
}
