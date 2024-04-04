package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.OfferDto;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

public interface OffersService {
    ResponseEntity<String> offerSending(OfferDto offerDto, NotificationDto notificationDto);

    void deleteOffersExceptOne(Long shopId, Long offerId);
}
