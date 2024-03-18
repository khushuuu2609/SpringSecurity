package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.ShopDto;
import com.example.SpringSecurity.Entity.Shop;
import com.example.SpringSecurity.Repository.ShopRepository;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;

    public ShopService(ShopRepository shopRepository, ModelMapper modelMapper) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
    }

    public void saveShop(ShopDto shopDto) {
        Shop shop = modelMapper.map(shopDto, Shop.class);
        shopRepository.save(shop);
    }

    public static String encodeImage(byte[] imageBytes) {
        return Base64.encodeBase64String(imageBytes);
    }

    public static byte[] decodeImage(String base64String) {
        return Base64.decodeBase64(base64String);
    }


    public ShopDto mapShopToDto(Shop shop) {
        return modelMapper.map(shop, ShopDto.class);
    }

    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

}
