package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private User UserId;
    private MultipartFile photo;
    private String categories;
    private String description;
    private String status;
    private Long price;
    private String productName;
    private LocalDateTime orderTime;
    // Getters and setter
}
