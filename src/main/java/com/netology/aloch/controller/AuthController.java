package com.netology.aloch.controller;

import com.netology.aloch.entity.UserMyDB;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class AuthController {


    @RequestMapping("hello")
    public String helloWorld(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello "+name+"!!";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
//    public ResponseEntity options(HttpServletResponse response) {
//        //log.info("OPTIONS /foo called");
//        //response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
//        return new ResponseEntity(HttpStatus.OK);
//    }

    //@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "Access-Control-Allow-Origin", allowCredentials = "true")
    @PostMapping("/login")
    //@RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
    public UserMyDB login(@RequestParam("login") String login, @RequestParam("password") String pwd) {

        String token = getJWTToken(login);
        UserMyDB user = new UserMyDB();
        user.setLogin(login);
        user.setToken(token);
        return user;

    }

//    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
//    public int login() {
//
//        return 200;
//
//    }

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
