package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;

import java.io.IOException;
import java.util.List;

public interface ShopNotificationService {
    void placeOrder(ShopDto shopDto, NotificationDto notificationDto) throws IOException;

    List<Shop> getShopOrdersByUserId(Long userId);
}
