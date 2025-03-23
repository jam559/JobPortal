package com.jobportal.jobportal_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.entity.JobSeekerApply;
import com.jobportal.jobportal_demo.entity.JobSeekerProfile;
import com.jobportal.jobportal_demo.repository.JobSeekerApplyRepository;

@Service
@SpringBootApplication
public class JobSeekerApplyService {

    @Autowired
    JobSeekerApplyRepository jobSeekerApplyRepository;

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId) {
        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job) {
        return jobSeekerApplyRepository.findByJob(job);
    }

    public void addNew(JobSeekerApply jobSeekerApply) {
        jobSeekerApplyRepository.save(jobSeekerApply);
    }
}
