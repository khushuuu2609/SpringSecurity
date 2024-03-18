package com.example.SpringSecurity.Dao.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String username;
    private String password;
    private String email;
    private String pin_code;
    private String address;
    private String city;
}
