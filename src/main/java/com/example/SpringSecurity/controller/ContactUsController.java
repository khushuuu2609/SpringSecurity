package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.Dao.Request.ContactFormDto;
import com.example.SpringSecurity.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:5173" },
        allowedHeaders = "*", allowCredentials="true")
@RestController
public class ContactUsController {

    @Autowired
    ContactService contactService;

    @PostMapping("/api/contact-us")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactFormDto contactFormDTO) {
        return contactService.contactus(contactFormDTO);
    }
}