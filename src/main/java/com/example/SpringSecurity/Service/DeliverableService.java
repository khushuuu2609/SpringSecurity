package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Entity.*;

public interface DeliverableService {

    Deliverables saveDeliverables(User user, Shop shop, SellerReg seller, Offers offer);
}
