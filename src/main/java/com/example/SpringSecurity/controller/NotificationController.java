package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications(HttpServletRequest request) {
        try {
            // Get logged-in user's area_name from the request
            User user = (User) request.getAttribute("user");
            String userAreaName = user.getAreaName();

            // Fetch all notifications from the database
            List<Notification> notifications = notificationRepository.findAll();

            // Filter notifications based on user's area_name
            List<Notification> filteredNotifications = notifications.stream()
                    .filter(notification -> {
                        // Find the corresponding seller for the notification
                        SellerReg seller = sellerRepository.findByCategoriesAndAreaName(notification.getCategories(), userAreaName);
                        return seller != null && Arrays.asList(seller.getCategories()).contains(notification.getCategories());
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(filteredNotifications, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions, return 500 Internal Server Error
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
