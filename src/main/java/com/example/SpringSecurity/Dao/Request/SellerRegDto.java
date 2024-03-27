package com.example.SpringSecurity.Dao.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerRegDto {

    private String[] categories;
    private String description;
    private String area_name;
    private String city;
    private String pin_code;
    private String userId;
}