package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface ShopNotificationService {
    ResponseEntity<String> placeOrder(ShopDto shopDto, NotificationDto notificationDto, HttpSession session);
}
