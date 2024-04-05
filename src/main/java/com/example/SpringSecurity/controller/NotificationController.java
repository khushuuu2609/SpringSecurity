package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.Role;
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

@CrossOrigin(origins = { "http://localhost:5173" },
        allowedHeaders = "*", allowCredentials="true")
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
            Role userRole = user.getRole();

            if (userRole != Role.SELLER) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

            String userAreaName = user.getAreaName();
            List<Notification> notifications = notificationRepository.findAll();
            List<SellerReg> sellersInUserArea = sellerRepository.findByAreaName(userAreaName);

            List<Notification> filteredNotifications = new ArrayList<>();
            for (Notification notification : notifications) {
                for (SellerReg seller : sellersInUserArea) {
                    // Exclude user's own notifications if they are also a seller
                    if (seller.getUser().getId().equals(userId) &&
                            Arrays.asList(seller.getCategories()).contains(notification.getCategories()) &&
                            userAreaName.equals(notification.getUser().getAreaName())) {
                        // Populate sellerIdArr in the notification
                        String categories = notification.getCategories();
                        String[] categoriesArray = {categories}; // Convert single string to array of strings
                        List<Long> sellerIds = sellerRepository.findSellerIdsByAreaNameAndCategories(notification.getUser().getAreaName(), categoriesArray);                        notification.setSellerIdArr(sellerIds);
                        filteredNotifications.add(notification);
                        break;
                    }
                }
            }

            return ResponseEntity.ok(filteredNotifications);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

