package com.netology.aloch;

//import com.netology.aloch.controller.JWTAuthorizationFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class CloudStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStorageApplication.class, args);
    }

    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().configurationSource(corsConfigurationSource()).and()
                    .csrf().disable();

//                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                    .antMatchers(HttpMethod.POST, "/**").permitAll()
//                    .antMatchers(HttpMethod.GET, "/**").permitAll()
//                    .antMatchers(HttpMethod.GET, "/login").permitAll()
//                    .antMatchers(HttpMethod.PUT, "/file").permitAll()
//                    .antMatchers(HttpMethod.POST, "/logout").permitAll()
//                    .antMatchers(HttpMethod.GET, "/list").permitAll()
//                    //.antMatchers(HttpMethod.POST, "/file").permitAll()
//                    .antMatchers(HttpMethod.GET, "/file").permitAll()
//                    .antMatchers(HttpMethod.DELETE, "/file").permitAll()
//                    .anyRequest().authenticated();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedOrigins(List.of("http://localhost:8080"));
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setMaxAge(Long.valueOf(3600));
            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
            configuration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            return source;
        }
    }

}
