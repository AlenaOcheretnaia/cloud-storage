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

    // *** Login, check credentials ***
    @PostMapping("/login")
    public ResponseEntity loginCheck(@RequestBody UserMyDB userLogin) {
            userService.checkUserDB(userLogin.getLogin(), userLogin.getPassword());
            String token = getJWTToken(userLogin.getLogin());
            tokenService.assignTokenToUser(userLogin.getLogin(), token);
            JSONObject resp = new JSONObject();
            try {
                resp.put("auth-token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    // *** Logout redirect to <Location> header, no actions ***
    @PostMapping("/logout")
    public ResponseEntity logoutUser() {
            return new ResponseEntity<>(HttpStatus.OK);
    }
    // *** Logout user, deactivate Token ***
    @GetMapping("/login")
    public ResponseEntity loginLogoutUser(@RequestHeader("auth-token") String token) {
        tokenService.unassignToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // *** Generate Auth-Token ***
    private String getJWTToken(String username) {
//        String secretKey = "SecretAlOch";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
//        return token;

        return "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoidXNlcjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjM5NDE3MDc1LCJleHAiOjE2Mzk0MTc2NzV9.RiTNcwcqh-d4LQ3MkNlfFTn68nUy5aLhLOkqEl743ssgk23HC0keL8ZAIDDas1oqHOpwYn5Yi7TyphRVZ6Z5XQ";
    }
}