package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.Role;
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
public class Signup {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String pin_code;
    private String address;
    private String city;
    private String area_name;
    private Role role;
}


