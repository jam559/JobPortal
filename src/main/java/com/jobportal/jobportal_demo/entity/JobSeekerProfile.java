package com.jobportal.jobportal_demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class JobSeekerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userAccountId;

    private String city;
    private String country;
    private String employmentType;
    private String firstName;
    private String lastName;
    @Column(nullable = true)
    private String profilePhoto;
    private String resume;
    private String province;
    private String workAuthorization;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userAccountId", referencedColumnName = "userId")
    @MapsId
    private Users user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
    List<Skills> skills;
}
