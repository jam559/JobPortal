package com.jobportal.jobportal_demo.entity;

public interface RecruiterJobsAccessor {

    Long getTotalCandidates();

    Integer getJobPostId();

    String getJobTitle();

    Integer getLocationId();

    String getCity();

    String getProvince();

    String getCountry();

    Integer getCompanyId();

    String getName();
}
