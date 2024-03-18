package com.example.SpringSecurity.Dao.Request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Signin {

    private String id;
    private String email;
    private String password;

}
