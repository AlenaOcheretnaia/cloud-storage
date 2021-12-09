package com.netology.aloch.controller;

import com.google.gson.Gson;
import com.netology.aloch.model.ErrorApp;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.service.TokenService;
import com.netology.aloch.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity loginCheck(@RequestBody UserMyDB userLogin) {
        if (userService.checkUserDB(userLogin.getLogin(), userLogin.getPassword())) {
            String token = getJWTToken(userLogin.getLogin());
            tokenService.assignTokenToUser(userLogin.getLogin(), token);
            JSONObject resp = new JSONObject();
            try {
                resp.put("auth-token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        } else {
            String errResp = new Gson().toJson(new ErrorApp("Bad Credentials", 400));
            return new ResponseEntity<>(errResp, HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser() {
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity loginLogoutUser(@RequestHeader("auth-token") String token) {
        tokenService.unassignToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getJWTToken(String username) {
        String secretKey = "SecretAlOch";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}