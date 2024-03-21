package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.SellerRegDto;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Entity.User;
import com.example.SpringSecurity.Repository.SellerRepository;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.SellerRegService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SellerRegServiceImpl implements SellerRegService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> sellerReg(@RequestBody SellerRegDto sellerRegDto, HttpSession session) {

        Long id = (Long) session.getAttribute("userId");

        if (id != null) {
            User user = userRepository.findById(id).orElseThrow();
            user.setId(id);

            SellerReg sellerReg = new SellerReg();
            sellerReg.setCategories(sellerRegDto.getCategories());
            sellerReg.setDescription(sellerRegDto.getDescription());
            sellerReg.setId(user);

            sellerRepository.save(sellerReg);
            return ResponseEntity.ok("Successfully Saved");
        }
        return ResponseEntity.badRequest().body("Not saved!!");
    }
}
