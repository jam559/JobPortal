package com.jobportal.jobportal_demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.IRecruiterJobs;
import com.jobportal.jobportal_demo.entity.JobCompany;
import com.jobportal.jobportal_demo.entity.JobLocation;
import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.entity.RecruiterJobsDto;
import com.jobportal.jobportal_demo.repository.JobPostActivityRepository;

@Service
@SpringBootApplication
public class JobPostActivityService {

    @Autowired
    JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
        
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getProvince(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found"));
    }

}
