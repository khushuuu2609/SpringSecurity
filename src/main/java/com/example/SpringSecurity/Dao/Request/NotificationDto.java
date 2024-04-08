package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.Shop;
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
public class NotificationDto {
    private String title;
    private String description;
    private MultipartFile photo;
    private String categories;
    private String username;
    private LocalDateTime orderTime;
    private User UserId;

}