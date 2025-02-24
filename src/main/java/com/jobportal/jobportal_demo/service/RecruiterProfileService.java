package com.jobportal.jobportal_demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.RecruiterProfile;
import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.repository.RecruiterProfileRepository;

@SpringBootApplication
@Service
public class RecruiterProfileService {
    
    @Autowired
    RecruiterProfileRepository recruiterProfileRepository;

    public Optional<RecruiterProfile> getProfileByUser(Integer id){
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);
    }
}
