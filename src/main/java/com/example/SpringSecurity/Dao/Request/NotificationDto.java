package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
}