//package com.netology.aloch.controller;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.netology.aloch.repository.TokenRepository;
//import com.netology.aloch.service.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//public class JWTAuthorizationFilter extends OncePerRequestFilter {
//
//    private final String HEADER = "auth-token";
//    private final String PREFIX = "Bearer ";
//    private final String SECRET = "SecretAlOch";
//
//    @Autowired
//    private TokenService tokenService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        try {
//            String authenticationHeader = request.getHeader(HEADER);
//            if (authenticationHeader == null || !tokenService.checkToken(authenticationHeader)) {
//                String username = tokenService.findUserByToken(authenticationHeader);
//            } else
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            return;
//        }
//    }
//
//    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
//        String authenticationHeader = request.getHeader(HEADER);
//        if (authenticationHeader == null || !tokenService.checkToken(authenticationHeader))
//            return false;
//        return true;
//    }
//
//}
