package com.example.SpringSecurity.Service;


import org.springframework.stereotype.Service;

@Service
public interface ForgotPasswordService {
    String generateOTP();


}