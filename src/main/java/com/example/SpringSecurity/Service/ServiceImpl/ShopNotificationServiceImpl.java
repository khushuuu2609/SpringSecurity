package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.JwtService;
import com.example.SpringSecurity.Service.ShopNotificationService;
import jakarta.servlet.http.HttpSession;
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

    public ResponseEntity<String> placeOrder(@ModelAttribute ShopDto shopDto, NotificationDto notificationDto, HttpSession session) {
        try {
            // Convert MultipartFile to base64-encoded string
            String base64Photo = Base64.getEncoder().encodeToString(shopDto.getPhoto().getBytes());


            // Convert ShopDto to Shop entity


            String userName = (String) session.getAttribute("username");
            Long id = (Long) session.getAttribute("userId");

            if (id != null) {
                User user = userRepository.findById(id).orElseThrow();
                user.setId(id);

                Shop shop = new Shop();
                shop.setPhoto(base64Photo.getBytes());
                shop.setCategories(shopDto.getCategories());
                shop.setDescription(shopDto.getDescription());
                shop.setUser(user);
                String open = "OPEN";
                shop.setStatus(open);

                session.setAttribute("shopId", shop.getShopId());
                // Save the shop in the database
                shopRepository.save(shop);

                Notification notification = new Notification();
                notification.setUser(user);
                notification.setTitle(userName);
                notification.setDescription(notificationDto.getDescription());
                notification.setShopId(shop);
                notification.setCategories(shopDto.getCategories());
                notification.setPhoto(shopDto.getPhoto().getBytes());

                notificationRepository.save(notification);

                return ResponseEntity.badRequest().body("userId is getting");
            }


            return ResponseEntity.ok("Order placed successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the photo");
    }
    }
}
