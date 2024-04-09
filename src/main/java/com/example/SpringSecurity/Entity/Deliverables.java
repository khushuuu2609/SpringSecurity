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
    private long DeliverablesId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private long userId;

    @JoinColumn(name="shopId",referencedColumnName = "shopId")
    private long shopId;

    @JoinColumn(name = "seller_id", referencedColumnName = "SellerId")
    private long sellerId;

    @JoinColumn(name = "offer_id", referencedColumnName = "offerId")
    private long offerId;

}
