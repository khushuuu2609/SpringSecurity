package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.JwtService;
import com.example.SpringSecurity.Service.ShopNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.Base64;

@Service
public class ShopNotificationServiceImpl implements ShopNotificationService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    public ResponseEntity<String> placeOrder(@ModelAttribute ShopDto shopDto, NotificationDto notificationDto) {
        try {
            // Convert MultipartFile to base64-encoded string
            String base64Photo = Base64.getEncoder().encodeToString(shopDto.getPhoto().getBytes());


                Shop shop = new Shop();
                shop.setPhoto(base64Photo.getBytes());
                shop.setCategories(shopDto.getCategories());
                shop.setDescription(shopDto.getDescription());
                String open = "OPEN";
                shop.setStatus(open);
                shop.setUser(shopDto.getUserId());

                shopRepository.save(shop);

                Notification notification = new Notification();
                notification.setDescription(shopDto.getDescription());
                notification.setShopId(shop);
                notification.setCategories(shopDto.getCategories());
                notification.setPhoto(shopDto.getPhoto().getBytes());
                notification.setUser(notificationDto.getUserId());
                notification.setTitle(notificationDto.getUsername());


                notificationRepository.save(notification);



            return ResponseEntity.ok("Order placed successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the photo");
    }
    }
}
