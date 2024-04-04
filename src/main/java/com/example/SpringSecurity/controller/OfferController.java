package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.OfferDto;
import com.example.SpringSecurity.Entity.Offers;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.OfferRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.ServiceImpl.OffersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/img")
public class OfferController {



    @Autowired
    OffersServiceImpl offersService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OfferRepository offerRepository;
    @PostMapping("/offer")
    public ResponseEntity<String> offerProduct(@ModelAttribute NotificationDto notificationDto, OfferDto offerDto) {
        if (notificationDto.getPhoto() != null) {
            User user = userRepository.findById(notificationDto.getUserId().getId()).orElse(null);
            if (user != null) {
                offersService.offerSending(offerDto, notificationDto);
                return ResponseEntity.ok("Offer sent successfully");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        }
        return ResponseEntity.badRequest().body("Photo is required");
    }


    @GetMapping("/offers")
    public ResponseEntity<List<Offers>> getAllOffers() {
        try {
            List<Offers> offers = offerRepository.findAll();
            return ResponseEntity.ok(offers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/offertoUser")
    public ResponseEntity<List<Offers>> getOffersByUserId(@RequestParam Long userId) {
        try {
            List<Offers> offers = offerRepository.findByUserId(userId);
            return ResponseEntity.ok(offers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
