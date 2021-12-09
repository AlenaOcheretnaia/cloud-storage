package com.netology.aloch.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorApp {
    private String message;
    private int id;
}
