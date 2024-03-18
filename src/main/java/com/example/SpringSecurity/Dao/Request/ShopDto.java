package com.example.SpringSecurity.Dao.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private MultipartFile photo;
    private String categories;
    private String description;
    private String status;
    // Getters and setters
}
