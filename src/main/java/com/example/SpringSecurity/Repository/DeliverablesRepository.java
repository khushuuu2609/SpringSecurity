package com.example.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.SpringSecurity.Entity.Deliverables;

@Repository
public interface DeliverablesRepository extends JpaRepository<Deliverables, Long> {
    // You can add custom query methods here if needed
}
