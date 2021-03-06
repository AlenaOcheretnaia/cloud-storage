package com.netology.aloch.auth;

import java.util.ArrayList;

import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserMyDB userMyDB = userService.findUserByName(username);
        return new User(username, userMyDB.getPassword(), new ArrayList<>());
    }
}