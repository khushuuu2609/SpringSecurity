package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.Role;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Service.ShopNotificationService;
import com.example.SpringSecurity.Service.UserService;
import com.example.SpringSecurity.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173"},
        allowedHeaders = "*", allowCredentials = "true")
@Slf4j
@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopNotificationService shopNotificationService;

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications(@Param("userId") Long userId) {
        try {
            User user = userService.getUserById(userId);
            Role userRole = user.getRole();

            if (userRole != Role.SELLER) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

            String userAreaName = user.getAreaName();
            List<Notification> notifications = notificationRepository.findAll();
            List<SellerReg> sellersInUserArea = sellerRepository.findByAreaName(userAreaName);

            List<Notification> filteredNotifications = new ArrayList<>();
            for (Notification notification : notifications) {
                boolean isSellerOwnNotification = false;
                for (SellerReg seller : sellersInUserArea) {
                    if (notification.getUser().getId().equals(userId)) {
                        isSellerOwnNotification = true;
                        break;
                    }
                }
                if (!isSellerOwnNotification &&
                        userAreaName.equals(notification.getUser().getAreaName())) {
                    filteredNotifications.add(notification);
                }
            }

            return ResponseEntity.ok(filteredNotifications);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteByShopId/{shopId}")
    public ResponseEntity<String> deleteNotificationsByShopId(@PathVariable Long shopId) {

        if (shopNotificationService.deleteNotificationsByShopId(shopId)) {
            return Util.getResponseEntity("Notification deleted successfully", HttpStatus.OK);
        }
        return Util.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

