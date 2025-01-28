package com.jobportal.jobportal_demo.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        boolean isJobSeeker = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Job Seeker"));
        boolean isRecruiter = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Recruiter"));
        if(isJobSeeker || isRecruiter) {
            response.sendRedirect("/dashboard/");
        }
    }
}
