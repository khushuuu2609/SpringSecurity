package com.example.SpringSecurity.Service.ServiceImpl;

import com.example.SpringSecurity.Dao.Request.ContactFormDto;
import com.example.SpringSecurity.Entity.ContactForm;
import com.example.SpringSecurity.Repository.ContactFormRepository;
import com.example.SpringSecurity.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ContacctServiceImpl implements ContactService {

    @Autowired
    ContactFormRepository contactFormRepository;

        public ResponseEntity<String> contactus(@RequestBody ContactFormDto contactFormDTO){
            // Create a ContactForm entity from the DTO
            ContactForm contactForm = new ContactForm();
            contactForm.setUsername(contactFormDTO.getUsername());
            contactForm.setEmail(contactFormDTO.getEmail());
            contactForm.setMessage(contactFormDTO.getMessage());

            // Save the contact form data to the database
            contactFormRepository.save(contactForm);

            return ResponseEntity.ok("Sucessfully Saved");

        }
}
