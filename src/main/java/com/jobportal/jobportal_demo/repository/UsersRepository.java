package com.jobportal.jobportal_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.jobportal_demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
    Users getUsersByEmail(String email);
}
