package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Config.EmailService;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not found in database.");
        }else{
            String otp = forgotPasswordService.generateOTP();

            session.setAttribute("sessionOtp", otp);
            session.setAttribute("useremail", email);

            boolean otpSent = emailService.sendEmail("Password Reset OTP", "Your OTP is: " + otp, email);

            if (otpSent) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP.");
            }
        }
    }



    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestParam("otp") String otp) {
        if (session != null) {
            var storedOtp = session.getAttribute("sessionOtp");
//            var email = session.getAttribute("email");

            System.out.println("Stored OTP: " + storedOtp);

            if (storedOtp != null && storedOtp.equals(otp)) {

                session.removeAttribute("otp");
                session.removeAttribute("email");
                System.out.println("OTP verified successfully");
                return ResponseEntity.ok().build();
            } else {
                // Invalid OTP
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
            }
        } else {
            // Session not available or expired
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired or not available.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("password") String newPassword) {
        // Retrieve the email of the user from the session
        var email = session.getAttribute("useremail");
        System.out.println("Email: of the user"+email);

        if (email == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not found in session.");
        }


        User user = userService.findByEmail((String) email);
     if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }


        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password cannot be the same as the old password.");
        }


        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        session.removeAttribute("email");
        return ResponseEntity.ok("Password updated successfully.");
    }
}
