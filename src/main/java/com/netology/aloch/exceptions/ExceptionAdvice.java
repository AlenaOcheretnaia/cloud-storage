package com.netology.aloch.exceptions;

import com.google.gson.Gson;
import com.netology.aloch.model.ErrorApp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadCredentials.class)
    public ResponseEntity<String> handleBadCredentialsE(BadCredentials e) {
        String errResp = new Gson().toJson(new ErrorApp("Bad Credentials", 400));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
    }

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<String> handleErrorInputDataE(ErrorInputData e) {
        String errResp = new Gson().toJson(new ErrorApp("Error Input Data", 400));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);
    }

    @ExceptionHandler(UnauthorizedError.class)
    public ResponseEntity<String> handleUnauthorizedErrorE(UnauthorizedError e) {
        String errResp = new Gson().toJson(new ErrorApp("Unauthorized error", 401));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errResp);
    }

    @ExceptionHandler(ErrorDeleteFile.class)
    public ResponseEntity<String> handleErrorDeleteFileE(ErrorDeleteFile e) {
        String errResp = new Gson().toJson(new ErrorApp("Error delete file", 500));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResp);
    }

}
