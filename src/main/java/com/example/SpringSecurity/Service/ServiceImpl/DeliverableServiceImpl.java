package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Entity.*;
import com.example.SpringSecurity.Repository.DeliverablesRepository;
import com.example.SpringSecurity.Service.DeliverableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverableServiceImpl implements DeliverableService {

    private final DeliverablesRepository deliverablesRepository;

    @Autowired
    public DeliverableServiceImpl(DeliverablesRepository deliverablesRepository) {
        this.deliverablesRepository = deliverablesRepository;
    }

    @Override
    public Deliverables saveDeliverables(User user, Shop shop, SellerReg seller, Offers offer) {
        Deliverables deliverables = new Deliverables();
        deliverables.setUser(user);
        deliverables.setShop(shop);
        deliverables.setSeller(seller);
        deliverables.setOffer(offer);
        return deliverablesRepository.save(deliverables);
    }

    @Override
    public List<Deliverables> getDeliverablesBySellerId(SellerReg sellerId) {
        return deliverablesRepository.findBySeller(sellerId);
    }


}
