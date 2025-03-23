package com.jobportal.jobportal_demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import com.jobportal.jobportal_demo.entity.RecruiterJobsAccessor;
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

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter) {
        List<RecruiterJobsAccessor> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);
        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
        for (RecruiterJobsAccessor rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getProvince(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobsDtoList.add(
                    new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJobPostId(), rec.getJobTitle(), loc, comp));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote,
            LocalDate searchDate) {
        return Objects.isNull(searchDate) ? jobPostActivityRepository.searchWithoutDate(job, location, remote, type)
                : jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }

}
