package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<ShopDto> getShopsByUserId(Long userId);
}

