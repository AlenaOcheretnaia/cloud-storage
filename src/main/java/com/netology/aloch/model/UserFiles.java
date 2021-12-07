package com.netology.aloch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_content")
public class UserFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String userName;

    int fileId;
}
