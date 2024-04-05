package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Service.ShopNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin(origins = { "http://localhost:5173" },
        allowedHeaders = "*", allowCredentials="true")
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
        return ResponseEntity.ok("order is placed successfully");
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
            shop.setStatus(newStatus);
            shopRepository.save(shop);
            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("shopdis/{shopId}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long shopId) {
        try {
            Shop shop = shopRepository.findById(shopId).orElse(null);
            if (shop != null) {
                return ResponseEntity.ok(shop);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{shopId}/price")
    public ResponseEntity<Void> updatePrice(@PathVariable Long shopId, @RequestParam Long price) {
        shopNotificationService.updatePrice(shopId, price);
        return ResponseEntity.noContent().build();
    }

}

