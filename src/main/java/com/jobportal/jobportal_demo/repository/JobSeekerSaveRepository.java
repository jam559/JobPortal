package com.jobportal.jobportal_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.jobportal_demo.entity.JobPostActivity;
import com.jobportal.jobportal_demo.entity.JobSeekerProfile;
import com.jobportal.jobportal_demo.entity.JobSeekerSave;

public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);

}
