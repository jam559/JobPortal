package com.jobportal.jobportal_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.jobportal_demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    public Users getUsersByEmail(String email);
}
