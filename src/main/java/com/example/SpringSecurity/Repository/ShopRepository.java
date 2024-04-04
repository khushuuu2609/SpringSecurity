package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByUserId(Long userId);

    @Modifying
    @Query("UPDATE Shop s SET s.price = :price WHERE s.id = :shopId")
    void updatePrice(@Param("shopId") Long shopId, @Param("price") Long price);

}

