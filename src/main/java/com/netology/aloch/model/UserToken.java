package com.netology.aloch.model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tokens")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String token;

    public UserToken(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
