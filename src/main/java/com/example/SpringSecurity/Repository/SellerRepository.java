package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.SellerReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SellerRepository extends JpaRepository<SellerReg,Long> {
    @Query(value = "SELECT * FROM seller_reg WHERE user_id LIKE :id", nativeQuery = true)
    SellerReg findByUserId(Long id);

    List<SellerReg> findByAreaName(String userAreaName);

    @Query(value = "SELECT sellerId FROM SellerReg s WHERE s.areaName = :areaName AND s.categories IN :categories")
    List<Long> findSellerIdsByAreaNameAndCategories(@Param("areaName") String areaName, @Param("categories") String[] categories);
}