package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
//    @Modifying
    @Modifying
    @Transactional
    @Query(value = "Delete from Notification where shop_id = :shopId", nativeQuery = true)
    void deleteByShopId(Long shopId);
}
