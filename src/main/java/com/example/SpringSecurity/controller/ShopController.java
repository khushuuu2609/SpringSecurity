package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Service.ShopNotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/img")
public class ShopController {

    @Autowired
    private final ShopNotificationService shopNotificationService;

    @PostMapping("/shop")
    public ResponseEntity<String> placeShop(@ModelAttribute ShopDto shopDto, NotificationDto notificationDto, HttpSession session) {
        if (shopDto.getPhoto() == null) {
            return ResponseEntity.badRequest().body("Photo is required");
        }
        shopNotificationService.placeOrder(shopDto, notificationDto,session);
        return  ResponseEntity.ok("order is placed successfully");
    }
}