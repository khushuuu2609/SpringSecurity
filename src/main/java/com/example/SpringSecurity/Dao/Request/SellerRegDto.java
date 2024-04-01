package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.User;
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
    private String areaName;
    private String city;
    private String pin_code;
    private User userId;

}