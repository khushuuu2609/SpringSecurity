package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.ShopDto;

import java.util.List;

public interface ShopNotificationService {
    void placeOrder(ShopDto shopDto, NotificationDto notificationDto);

    List<ShopDto> getShopsByUserId(Long userId);
}
