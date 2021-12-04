package com.netology.aloch.service;

import com.netology.aloch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkUserDB(String name, String pwd){
        return userRepository.checkUserDB(name, pwd);
    }

//    public void assignTokenToUser(String token) {
//        //userRepository.assignTokenToUser();
//    }
}
