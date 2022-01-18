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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenService tokenService;

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
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(login);

            final String token = jwtTokenUtil.generateToken(userDetails);
            tokenService.assignTokenToUser(login, token);
            return token;
        } else {
            throw new BadCredentials("Bad Credentials");
        }
    }
}
