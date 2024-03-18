package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.JwtAuthenticationResponse;
import com.example.SpringSecurity.Dao.Request.Signin;
import com.example.SpringSecurity.Dao.Request.Signup;
import com.example.SpringSecurity.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Signup request) {
        return ResponseEntity.ok(authenticationService.signup(request)); // Assuming "register_success" is a success message or page
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody Signin request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
