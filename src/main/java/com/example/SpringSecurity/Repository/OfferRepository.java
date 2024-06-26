package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offers,Long>{

        List<Offers> findByUserId(Long userId);

        @Modifying
        @Query(value = "DELETE FROM offers WHERE shop_id = :shopId AND offer_id <> :offerId",nativeQuery = true)
        void deleteAllByShopIdAndIdNot(@Param("shopId") Long shopId, @Param("offerId") Long offerId);

}