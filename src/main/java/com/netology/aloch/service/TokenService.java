package com.netology.aloch.service;

import com.netology.aloch.exceptions.UnauthorizedError;
import com.netology.aloch.model.UserToken;
import com.netology.aloch.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public void assignTokenToUser(String username, String token) {
        UserToken userToken = new UserToken(username, token);
        tokenRepository.save(userToken);
    }

    public void unassignToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    public String findUserByToken(String token) {
        try {
            UserToken userToken = tokenRepository.findByToken(token).get();
            return userToken.getUsername();
        } catch (Exception e) {
            return null;
        }

    }

//    public boolean checkToken(String tokenReq) {
//        try {
//            tokenRepository.findByToken(tokenReq).get();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

}
