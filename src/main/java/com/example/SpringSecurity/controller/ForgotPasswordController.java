package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Config.EmailService;
import com.example.SpringSecurity.Dao.MessageResponse;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.ForgotPasswordService;
import com.example.SpringSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:5173" },
        allowedHeaders = "*", allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOTP(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");

        // Check if the email exists in the database
        if (userRepository.findByEmail(email).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Invalid credentials"));
        } else {
            String otp = forgotPasswordService.generateOTP();

            // Set expiration time for OTP (2 minutes in this example)
            int otpExpirationMinutes = 2;
            long otpExpirationTime = System.currentTimeMillis() + (otpExpirationMinutes * 60 * 1000);

            session.setAttribute("sessionOtp",otp);
            session.setAttribute("otpExpirationTime",otpExpirationTime);
            session.setAttribute("useremail",email);
            boolean otpSent = emailService.sendEmail("Password Reset OTP",
                    "Thank you for choosing LocalStore! Please use the following OTP to reset your password..!!\n" +
                            "\n" + "OTP:" + otp + "\n" +
                            "\n" + "Please enter this OTP on the verification page to confirm your email address. This OTP is valid for 2 minutes. If you did not request this OTP, please ignore this email.\n" +
                            "\n" + "Thank you,\n" + "The LocalStore Team",
                    email);
            if (otpSent) {
                return ResponseEntity.ok().body(new MessageResponse("OTP sent successfully."));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Failed to send OTP."));
            }
        }
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestParam("otp") String otp) {
        if (session != null) {
            var storedOtp = session.getAttribute("sessionOtp");
            var otpExpirationTime = session.getAttribute("otpExpirationTime");

            if (storedOtp != null && storedOtp.equals(otp)) {
                long currentTime = System.currentTimeMillis();
                if (otpExpirationTime != null && currentTime < (long) otpExpirationTime) {
                    session.removeAttribute("sessionOtp");
                    session.removeAttribute("otpExpirationTime");
                    session.removeAttribute("useremail");
                    System.out.println("OTP verified successfully");
                    return ResponseEntity.ok().body(new MessageResponse("OTP verified successfully."));
                } else {
                    // OTP has expired
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("OTP has expired."));
                }
            } else {
                // Invalid OTP
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid OTP."));
            }
        } else {
            // Session not available or expired
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Session expired or not available."));
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("password") String newPassword) {
        // Retrieve the email of the user from the session
        var email = session.getAttribute("useremail");
        System.out.println("Email: of the user" + email);

        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email not found in session."));
        }

        User user = userService.findByEmail((String) email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("User not found."));
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("New password cannot be the same as the old password."));
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        session.removeAttribute("email");
        return ResponseEntity.ok().body(new MessageResponse("Password updated successfully."));
    }
}
