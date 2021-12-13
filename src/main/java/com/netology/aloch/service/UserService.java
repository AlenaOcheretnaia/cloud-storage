package com.netology.aloch.service;

import com.netology.aloch.exceptions.BadCredentials;
import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void checkUserDB(String login, String password) {
        if (userRepository.findByLoginAndPassword(login, password).isEmpty()) {
            throw new BadCredentials("Bad Credentials");
        }
    }

}
