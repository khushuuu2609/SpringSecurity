package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Entity.*;

import java.util.List;

public interface DeliverableService {

    Deliverables saveDeliverables(User user, Shop shop, SellerReg seller, Offers offer);

    List<Deliverables> getDeliverablesBySellerId(SellerReg sellerId);
}
