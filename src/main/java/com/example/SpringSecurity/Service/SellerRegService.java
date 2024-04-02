package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.SellerRegDto;
import com.example.SpringSecurity.Entity.SellerReg;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface SellerRegService {

    ResponseEntity<String> sellerReg(SellerRegDto sellerRegDto,Long id);

    ResponseEntity<SellerReg> getSellerProfile(Long userId);

    ResponseEntity<String> updateSellerProfile(SellerReg sellerReg, Long userId);
}
