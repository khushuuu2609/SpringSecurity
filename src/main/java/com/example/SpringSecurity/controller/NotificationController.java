package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications(@Param("userId") Long userId) {
        try {

            User user = userService.getUserById(userId);
            String userAreaName = user.getAreaName();

            List<Notification> notifications = notificationRepository.findAll();

            List<SellerReg> sellersInUserArea = sellerRepository.findByAreaName(userAreaName);
            System.out.println(sellersInUserArea);

            List<Notification> filteredNotifications = new ArrayList<>();
            for (Notification notification : notifications) {
                for (SellerReg seller : sellersInUserArea) {
                    if (Arrays.asList(seller.getCategories()).contains(notification.getCategories())) {
                        filteredNotifications.add(notification);
                        break;
                    }
                }
            }

            System.out.println(filteredNotifications);

            return ResponseEntity.ok(filteredNotifications);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}