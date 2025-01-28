package com.jobportal.jobportal_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.jobportal_demo.entity.JobSeekerProfile;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer>{

}
