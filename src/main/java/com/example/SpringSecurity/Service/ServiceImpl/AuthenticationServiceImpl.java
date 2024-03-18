package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.JwtAuthenticationResponse;
import com.example.SpringSecurity.Dao.Request.Signin;
import com.example.SpringSecurity.Dao.Request.Signup;
import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.AuthenticationService;
import com.example.SpringSecurity.Service.JwtService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private HttpSession session;
    @Override
    public JwtAuthenticationResponse signup(Signup request) {
        var password = request.getPassword();
        var confirmPassword = request.getConfirmPassword();
        var email = request.getEmail();
        if (!password.equals(confirmPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JwtAuthenticationResponse.builder()
                            .error("Passwords do not match")
                            .build()).getBody();
        }

        Optional<User> UserByEmail = userRepository.findByEmail(email);
        if (UserByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(JwtAuthenticationResponse.builder()
                            .error("Email already exists")
                            .build()).getBody();
        }

        var member = User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .confirmPassword(passwordEncoder.encode(request.getConfirmPassword()))
                .pin_code(request.getPin_code())
                .address(request.getAddress())
                .city(request.getCity())
                .role(Role.USER)
                .build();
        userRepository.save(member);
        var jwt = jwtService.generateToken(member);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    @Override
    public JwtAuthenticationResponse signin(Signin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                        request.getPassword()));

        var member = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find'"));

        var jwt = jwtService.generateToken(member);
        session.setAttribute("userId", member.getId());
        session.setAttribute("username", member.getUsername());
        return JwtAuthenticationResponse.builder().token(jwt).userId(String.valueOf(member.getId())).build();
    }
}
