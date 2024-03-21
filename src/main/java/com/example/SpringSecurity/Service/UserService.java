package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.UpdateProfileRequest;
import com.example.SpringSecurity.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    void updateUserProfile(Long id, UpdateProfileRequest request);

    User getUserById(Long id);
    Boolean updateToSeller(Long id);

    User findByEmail(String email);
}
