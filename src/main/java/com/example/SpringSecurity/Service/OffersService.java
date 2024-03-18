package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.OfferDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface OffersService {
    ResponseEntity<String> offerSending(OfferDto offerDto, HttpSession session);
}
