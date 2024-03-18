package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Service.ForgotPasswordService;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Override
    public String generateOTP() {
        int otpLength = 6;
        String numbers = "0123456789";
        StringBuilder otp = new StringBuilder(otpLength);
        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }


}
