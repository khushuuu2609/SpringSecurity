package com.example.SpringSecurity.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus)
    {
        return new ResponseEntity<String>(message, httpStatus);
    }
}
