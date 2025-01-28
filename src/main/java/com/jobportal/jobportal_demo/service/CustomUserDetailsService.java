package com.jobportal.jobportal_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.repository.UsersRepository;
import com.jobportal.jobportal_demo.util.CustomUserDetails;

import lombok.AllArgsConstructor;

@Service
@SpringBootApplication
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UsersRepository usersRepository;
        
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = null;
        try{
            user = usersRepository.getUsersByEmail(username);
        } catch(UsernameNotFoundException e){
            System.out.println("User is not found");
        }
        return new CustomUserDetails(user);
    }
}
