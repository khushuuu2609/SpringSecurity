package com.example.SpringSecurity.Dao;

import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Entity.Shop;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtAuthenticationResponse {

    private String token;
    private String error;
    private long userId;
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                