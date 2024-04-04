package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.NotificationDto;
import com.example.SpringSecurity.Dao.Request.OfferDto;
import com.example.SpringSecurity.Entity.Offers;
import com.example.SpringSecurity.Repository.OfferRepository;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Repository.ShopRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.OffersService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OffersServiceImpl implements OffersService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Override
    @Transactional
    public ResponseEntity<String> offerSending(OfferDto offerDto, NotificationDto notificationDto) {

        if (offerDto.getPhoto() == null) {
            return ResponseEntity.badRequest().body("Photo is required");
        }

        try {

                    Offers offer = new Offers();
                    byte[] bytes = offerDto.getPhoto().getBytes();
                    offer.setPhoto(bytes);
                    offer.setCategories(offerDto.getCategories());
                    offer.setDescription(offerDto.getDescription());
                    offer.setPrice(offerDto.getPrice());
                    offer.setSeller(offerDto.getSellerId());
                    offer.setUser(offerDto.getUserId());
                    offer.setShop(offerDto.getShopId());

            System.out.println(offer);

                    offerRepository.save(offer);


            return ResponseEntity.ok("Product was offered by seller");

        } catch (
                IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the photo");
        }
    }

    @Transactional
    public void deleteOffersExceptOne(Long shopId, Long offerId) {
        offerRepository.deleteAllByShopIdAndIdNot(shopId, offerId);
    }

}