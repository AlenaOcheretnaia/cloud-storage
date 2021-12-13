package com.netology.aloch.exceptions;

public class UnauthorizedError extends RuntimeException{
    public UnauthorizedError(String msg) {
        super(msg);
    }
}