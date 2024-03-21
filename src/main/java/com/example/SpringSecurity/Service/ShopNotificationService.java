package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShopNotificationService {
    ResponseEntity<String> placeOrder(ShopDto shopDto, NotificationDto notificationDto, HttpSession session);
}
