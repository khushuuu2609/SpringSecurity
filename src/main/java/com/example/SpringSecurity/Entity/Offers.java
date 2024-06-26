package com.example.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long offer_id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String description;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    @Column
    private  String Categories;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private String productName; // Add product name field

    @OneToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "shopId")
    private Shop shop;


    @OneToOne
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private SellerReg seller;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}