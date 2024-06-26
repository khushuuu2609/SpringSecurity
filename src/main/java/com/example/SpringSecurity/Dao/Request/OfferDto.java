package com.example.SpringSecurity.Dao.Request;

import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private int price;
    private String description;
    private String categories;
    private MultipartFile photo;
    private User UserId;
    private Shop shopId;
    private SellerReg sellerId;
    private String productName; // Add product name field
}