package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.OfferDto;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.ServiceImpl.OffersServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/img")
public class OfferController {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    OffersServiceImpl offersService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/offer")
    public ResponseEntity<String> offerProduct(@ModelAttribute NotificationDto notificationDto, OfferDto offerDto, HttpSession session) {
        if (notificationDto.getPhoto() != null) {
            User user = userRepository.findById(notificationDto.getUserId().getId()).orElse(null);
            if (user != null) {
                offersService.offerSending(offerDto, notificationDto , session );
                return ResponseEntity.ok("Offer sent successfully");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        }
        return ResponseEntity.badRequest().body("Photo is required");
    }
}
