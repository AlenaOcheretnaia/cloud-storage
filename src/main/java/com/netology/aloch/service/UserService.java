package com.netology.aloch.service;

import com.netology.aloch.auth.JwtTokenUtil;
import com.netology.aloch.auth.JwtUserDetailsService;
import com.netology.aloch.exceptions.BadCredentials;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    public boolean checkUserDB(String login, String password) {
        return !userRepository.findByLoginAndPassword(login, password).isEmpty();
    }

    public UserMyDB findUserByName(String username) {
        try {
            return userRepository.findByLogin(username);
        } catch (Exception e) {
            throw new BadCredentials("User not found with username: " + username);
        }
    }

    public String getToken(String login, String password) {
        if (checkUserDB(login, password)) {
            final UserDetails userDetails = jwtUserDetailsService
                    .loadUserByUsername(login);

            final String token = jwtTokenUtil.generateToken(userDetails);
            tokenService.assignTokenToUser(login, token);
            return token;
        } else {
            throw new BadCredentials("Bad Credentials");
        }
    }
}
