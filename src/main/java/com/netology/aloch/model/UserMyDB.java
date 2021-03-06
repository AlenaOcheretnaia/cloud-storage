package com.netology.aloch.model;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserMyDB {

    @Id
    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

}
