package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Notification;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.NotificationRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.JwtService;
import com.example.SpringSecurity.Service.ShopNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    SellerRepository sellerRepository;


    public void placeOrder(@ModelAttribute ShopDto shopDto, NotificationDto notificationDto) throws IOException {
        // Convert MultipartFile to base64-encoded string
        byte[] bytes = shopDto.getPhoto().getBytes();

        Shop shop = new Shop();
        shop.setPhoto(bytes);
        shop.setCategories(shopDto.getCategories());
        shop.setDescription(shopDto.getDescription());
        String open = "OPEN";
        shop.setStatus(open);
        shop.setUser(shopDto.getUserId());


        Shop savedShop = shopRepository.save(shop);


        Notification notification = new Notification();
        notification.setDescription(shopDto.getDescription());
        notification.setShopId(shop);
        notification.setCategories(shopDto.getCategories());
        notification.setPhoto(shopDto.getPhoto().getBytes());
        notification.setUser(notificationDto.getUserId());
        notification.setTitle(notificationDto.getUsername());

        notificationRepository.save(notification);

        ResponseEntity.ok("Order placed successfully");
    }


    @Override
    public List<Shop> getShopOrdersByUserId(Long userId) {
        return shopRepository.findByUserId(userId);
    }

}
