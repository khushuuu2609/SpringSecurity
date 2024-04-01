package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.SellerRegDto;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.SellerRegService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SellerRegServiceImpl implements SellerRegService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> sellerReg(SellerRegDto sellerRegDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        SellerReg sellerReg = new SellerReg();
        sellerReg.setCategories(sellerRegDto.getCategories());
        sellerReg.setDescription(sellerRegDto.getDescription());
        sellerReg.setAreaName(sellerRegDto.getAreaName());
        sellerReg.setPin_code(sellerRegDto.getPin_code());
        sellerReg.setCity(sellerRegDto.getCity());
        sellerReg.setUser(user);

        sellerRepository.save(sellerReg);
        return ResponseEntity.ok("Successfully Saved");
    }
}
