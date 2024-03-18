package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.SellerRegDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface SellerRegService {

    ResponseEntity<String> sellerReg(SellerRegDto sellerRegDto, HttpSession session);
}
