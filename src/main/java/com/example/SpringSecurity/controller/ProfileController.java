package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.UpdateProfileRequest;
import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Exception.UserNotFoundException;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.UserService;
import com.example.SpringSecurity.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class ProfileController {


    @Autowired
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        try {
            userService.updateUserProfile(id, request);
            return ResponseEntity.ok("User profile updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user profile");
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}