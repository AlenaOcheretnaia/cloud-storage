package com.netology.aloch.controller;

import com.netology.aloch.model.ErrorApp;
import com.netology.aloch.model.LoginForm;
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

    //login
    @PostMapping("/login")
    public ResponseEntity loginCheck(@RequestBody LoginForm loginForm) throws JSONException {
        System.out.println("Login = " + loginForm.getLogin() + ", password = " + loginForm.getPassword());
        if (userService.checkUserDB(loginForm.getLogin(), loginForm.getPassword())) {
            String token = getJWTToken(loginForm.getLogin());
            userService.assignTokenToUser(loginForm.getLogin(), token);
            JSONObject resp = new JSONObject();
            resp.put("auth-token", token);
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorApp("Bad credentials", 400));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logoutUser(@RequestHeader("auth-token") String token) {
        if (!token.isEmpty()) {
            userService.unAssignToken(token);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
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

        return "Bearer " + token;
    }
}
