//package com.netology.aloch.controller;
//
//import com.netology.aloch.service.TokenService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthTokenFilter extends OncePerRequestFilter {
//
//    private final String HEADER = "auth-token";
//    private final String PREFIX = "Bearer ";
//    private final String SECRET = "SecretAlOch";
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//    }
//
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//            if (checkJWTToken(request, response)) {
//
//            } else {
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            }
//        chain.doFilter(request, response);
//    }
//
//    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse response) {
//        String authenticationHeader = request.getHeader(HEADER);
//        System.out.println("SOUT checkJWTToken in DB = " + authenticationHeader);
//        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
//            String username;
//            try {
//                username = tokenService.findUserByToken(authenticationHeader);
//            } catch (Exception e) {
//                return false;
//            }
//            if (username.isEmpty()) {
//                return false;
//            } else
//                return true;
//        } return false;
//    }
//
//
//}