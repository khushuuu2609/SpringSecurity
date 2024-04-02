package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.ContactFormDto;
import com.example.SpringSecurity.Dao.Request.SellerRegDto;
import com.example.SpringSecurity.Entity.SellerReg;
import com.example.SpringSecurity.Repository.UserRepository;
import com.example.SpringSecurity.Service.SellerRegService;
import com.example.SpringSecurity.Service.UserService;
import com.example.SpringSecurity.utils.Util;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class SellerController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRegService sellerRegService;

    @Autowired
    HttpSession session;

    @CrossOrigin(origins = "http://localhost:5173")
    @PatchMapping("upgradeToSeller/{id}")
    public ResponseEntity<String> updateToSeller(@PathVariable Long id) {
        if (userService.updateToSeller(id))
            return Util.getResponseEntity("User updated to seller account", HttpStatus.OK);
        else
            return Util.getResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/seller")
    public ResponseEntity<String> registerSeller(@RequestBody SellerRegDto sellerRegDto, @RequestParam Long userId) {
        return sellerRegService.sellerReg(sellerRegDto, userId);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/seller/{userId}")
    public ResponseEntity<SellerReg> getSellerProfile(@PathVariable Long userId) {
        return sellerRegService.getSellerProfile(userId);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/seller/{userId}")
    public ResponseEntity<String> updateSellerProfile(@RequestBody SellerReg sellerReg, @PathVariable Long userId) {
        return sellerRegService.updateSellerProfile(sellerReg, userId);
    }
}
