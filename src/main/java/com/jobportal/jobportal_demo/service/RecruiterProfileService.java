package com.jobportal.jobportal_demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.RecruiterProfile;
import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.repository.RecruiterProfileRepository;
import com.jobportal.jobportal_demo.repository.UsersRepository;

@SpringBootApplication
@Service
public class RecruiterProfileService {

    @Autowired
    RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    UsersRepository usersRepository;

    public RecruiterProfile getProfileByUser(Integer id) {
        return recruiterProfileRepository.getReferenceById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }

    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = null;
            try {
                users = usersRepository.getUsersByEmail(currentUsername);
            } catch (UsernameNotFoundException e) {
                System.out.println("User not found");
            }

            Optional<RecruiterProfile> recruiterProfile = getOne(users.getUserId());
            return recruiterProfile.orElse(null);
        } else
            return null;
    }
}
