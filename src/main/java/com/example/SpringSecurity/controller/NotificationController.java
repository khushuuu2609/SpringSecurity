package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<List<Notification>> getAllNotifications(HttpServletRequest request) {
        try {
            // Get logged-in user's id from the request
            Long userId = userService.getUserIdFromRequest(request);

            // Find user's area name
            User user = userService.getUserById(userId);
            String userAreaName = user.getAreaName();

            // Fetch all notifications from the database
            List<Notification> notifications = notificationRepository.findAll();

            // Filter notifications based on user's area_name and categories
            List<Notification> filteredNotifications = notifications.stream()
                    .filter(notification -> {
                        // Find the corresponding seller for the notification
                        SellerReg seller = sellerRepository.findByCategoriesAndAreaName(notification.getCategories(), userAreaName);
                        return seller != null;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredNotifications, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as needed
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
