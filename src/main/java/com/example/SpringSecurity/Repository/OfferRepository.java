package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offers,Long>{

        }