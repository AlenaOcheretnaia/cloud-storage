package com.netology.aloch.service;

import com.netology.aloch.model.UserToken;
import com.netology.aloch.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public void assignTokenToUser(String username, String token) {
        UserToken userToken = new UserToken(username, "Bearer " + token);
        tokenRepository.save(userToken);
    }

    public String findUserByToken(String token) {
        UserToken userToken = tokenRepository.findByToken(token).get();
        return userToken.getUsername();
    }
}
