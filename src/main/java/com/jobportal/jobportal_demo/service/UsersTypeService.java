package com.jobportal.jobportal_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jobportal.jobportal_demo.entity.UsersType;
import com.jobportal.jobportal_demo.repository.UsersTypeRepository;

@SpringBootApplication
public class UsersTypeService {

    @Autowired
    UsersTypeRepository usersTypeRepository;
    
    public List<UsersType> getAll() {
        return usersTypeRepository.findAll();
    }
}
