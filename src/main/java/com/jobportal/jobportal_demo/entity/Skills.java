package com.jobportal.jobportal_demo.entity;

import org.springframework.jmx.export.annotation.ManagedAttribute;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String experienceLevel;
    private String yearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_seeker_profile", referencedColumnName = "userAccountId")
    private JobSeekerProfile jobSeekerProfile;
}
