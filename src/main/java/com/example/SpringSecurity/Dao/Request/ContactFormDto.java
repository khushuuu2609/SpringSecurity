package com.example.SpringSecurity.Dao.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormDto {

    private String username;
    private String email;
    private String message;

}