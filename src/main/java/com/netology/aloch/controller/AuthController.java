package com.netology.aloch.controller;

import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.service.TokenService;
import com.netology.aloch.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    // *** Login, Check credentials, Get Token ***
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody UserMyDB authRequest) {

        String token = userService.getToken(authRequest.getLogin(), authRequest.getPassword());

        Map<String, String> respToken = new HashMap<>();
        respToken.put("auth-token", token);
        JSONObject resp = new JSONObject(respToken);
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);

    }

    // *** Logout redirect to <Location> header, no actions ***
    @PostMapping("/logout")
    public void logoutUser() {
    }

    // *** Logout user, deactivate Token ***
    @GetMapping("/login")
    public void loginLogoutUser(@RequestHeader("auth-token") String token) {
        tokenService.unassignToken(token.substring(7));
    }

}