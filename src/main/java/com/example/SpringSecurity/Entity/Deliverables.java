package com.example.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Deliverables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliverablesId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "shopId")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId")
    private SellerReg seller;

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "offer_id")
    private Offers offer;

}
