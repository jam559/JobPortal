package com.jobportal.jobportal_demo.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jobportal.jobportal_demo.entity.JobSeekerProfile;
import com.jobportal.jobportal_demo.entity.RecruiterProfile;
import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.repository.JobSeekerProfileRepository;
import com.jobportal.jobportal_demo.repository.RecruiterProfileRepository;
import com.jobportal.jobportal_demo.repository.UsersRepository;

@SpringBootApplication
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    JobSeekerProfileRepository jobSeekerProfileRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUsers = usersRepository.save(users);
        Integer userTypeId = users.getUserTypeId().getUserTypeId();
        
        RecruiterProfile recruiterProfile = new RecruiterProfile();
        recruiterProfile.setUser(savedUsers);

        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
        jobSeekerProfile.setUser(savedUsers);

        if (userTypeId.equals(1)) {
            recruiterProfileRepository.save(recruiterProfile);
        } else {
            jobSeekerProfileRepository.save(jobSeekerProfile);
        }
        return savedUsers;
    }

    public Users getUsersByEmail(String email){
        return usersRepository.getUsersByEmail(email);
    }

    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users user = null;
            try{
                user = usersRepository.getUsersByEmail(username);
            } catch(UsernameNotFoundException e){
                System.out.println("Could not find the user!");
            }
            int userId = user.getUserId();
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
                RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
                return recruiterProfile;
            } else {
                JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
                return jobSeekerProfile;
            }
        }
        return null;
    }

    public Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String username = authentication.getName();
            Users user = null;
            try{
                user = usersRepository.getUsersByEmail(username);
            } catch(UsernameNotFoundException e){
                System.out.println("Could not find the user!");
            }
            return user;
        }
        return null;
    }
}
