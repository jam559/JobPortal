package com.jobportal.jobportal_demo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jobportal.jobportal_demo.entity.Users;
import com.jobportal.jobportal_demo.entity.UsersType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails{
    
    private Users user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        UsersType usersType = user.getUserTypeId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usersType.getUserTypeName()));
        return authorities;
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getEmail();
    }

}
