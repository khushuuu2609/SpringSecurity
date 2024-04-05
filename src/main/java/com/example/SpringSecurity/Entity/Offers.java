package com.example.SpringSecurity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id", referencedColumnName = "shopId")
    private Shop shop;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId")
    private SellerReg seller;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}