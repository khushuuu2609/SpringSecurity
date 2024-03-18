package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
