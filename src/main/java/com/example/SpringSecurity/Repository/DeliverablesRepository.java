package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.SellerReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringSecurity.Entity.Deliverables;

import java.util.List;

@Repository
public interface DeliverablesRepository extends JpaRepository<Deliverables, Long> {
    List<Deliverables> findBySeller(SellerReg sellerId);
    // You can add custom query methods here if needed
}
