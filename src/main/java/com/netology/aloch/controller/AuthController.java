package com.netology.aloch.controller;

import com.netology.aloch.auth.JwtTokenUtil;
import com.netology.aloch.auth.JwtUserDetailsService;
import com.netology.aloch.exceptions.BadCredentials;
import com.netology.aloch.model.UserMyDB;
import com.netology.aloch.service.TokenService;
import com.netology.aloch.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    // *** Login, check credentials ***
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserMyDB authenticationRequest) {

        if (userService.checkUserDB(authenticationRequest.getLogin(), authenticationRequest.getPassword())) {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getLogin());

            final String token = jwtTokenUtil.generateToken(userDetails);

            tokenService.assignTokenToUser(authenticationRequest.getLogin(), token);

            JSONObject resp = new JSONObject();
            try {
                resp.put("auth-token", token);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        } else {
            throw new BadCredentials("Bad Credentials");
        }
    }

    // *** Logout redirect to <Location> header, no actions ***
    @PostMapping("/logout")
    public ResponseEntity logoutUser() {
            return new ResponseEntity<>(HttpStatus.OK);
    }
    // *** Logout user, deactivate Token ***
    @GetMapping("/login?logout")
    public ResponseEntity loginLogoutUser(@RequestHeader("auth-token") String token) {
        tokenService.unassignToken(token.substring(7));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}