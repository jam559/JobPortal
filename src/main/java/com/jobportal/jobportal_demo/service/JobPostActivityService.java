package com.jobportal.jobportal_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.repository.JobPostActivityRepository;

@Service
@SpringBootApplication
public class JobPostActivityService {

    @Autowired
    JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }

}
