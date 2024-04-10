package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Entity.*;
import com.example.SpringSecurity.Service.DeliverableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:5173" },
        allowedHeaders = "*", allowCredentials="true")
@RestController
@RequestMapping("/api/deliverables")
public class DeliverableController {

    private final DeliverableService deliverableService;

    @Autowired
    public DeliverableController(DeliverableService deliverableService) {
        this.deliverableService = deliverableService;
    }

    @GetMapping("/save")
    public Deliverables saveDeliverables(
            @RequestParam User userId,
            @RequestParam Shop shopId,
            @RequestParam SellerReg sellerId,
            @RequestParam Offers offerId) {
        return deliverableService.saveDeliverables(userId, shopId, sellerId, offerId);
    }
}
