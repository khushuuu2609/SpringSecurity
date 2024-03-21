package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerReg,Long> {
    @Query(value = "SELECT * FROM seller_reg WHERE user_id LIKE :id", nativeQuery = true)
    SellerReg findByUserId(Long id);
}
