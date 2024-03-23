package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.UpdateProfileRequest;
import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user mot found"));
            }
        };
    }

    @Override
    public void updateUserProfile(Long id, UpdateProfileRequest request) throws com.example.SpringSecurity.Exception.UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new com.example.SpringSecurity.Exception.UserNotFoundException("User not found with ID: " + id));

        // Update user details
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if(request.getPin_code() != null){
            user.setPin_code(request.getPin_code());
        }
        if(request.getCity() != null)
            user.setCity(request.getCity());
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public Boolean updateToSeller(Long id) {
        User user = userRepository.findById(id).get();
        user.setRole(Role.SELLER);
        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

}
