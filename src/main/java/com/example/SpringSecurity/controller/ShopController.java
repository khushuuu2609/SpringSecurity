package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Service.ShopNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/img")
public class ShopController {

    @Autowired
    private final ShopNotificationService shopNotificationService;

    @Autowired
    ShopRepository shopRepository;

    @PostMapping("/shop")
    public ResponseEntity<String> placeShop(@ModelAttribute ShopDto shopDto, NotificationDto notificationDto) throws IOException {
        if (shopDto.getPhoto() == null) {
            return ResponseEntity.badRequest().body("Photo is required");
        }
        shopNotificationService.placeOrder(shopDto, notificationDto);
        return  ResponseEntity.ok("order is placed successfully");
    }

    @GetMapping("/shops/{userId}")
    public ResponseEntity<List<Shop>> getShopOrdersByUserId(@PathVariable Long userId) {
        List<Shop> shopOrders = shopNotificationService.getShopOrdersByUserId(userId);
        return ResponseEntity.ok(shopOrders);
    }


    @PutMapping("/status/{shopId}")
    public ResponseEntity<String> updateShopStatus(@PathVariable Long shopId, @RequestParam String newStatus) {
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            if (!shop.getStatus().equals("OPEN")) {
                return ResponseEntity.badRequest().body("Status can only be updated for orders with status OPEN");
            }
            shop.setStatus(newStatus);
            shopRepository.save(shop);
            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

