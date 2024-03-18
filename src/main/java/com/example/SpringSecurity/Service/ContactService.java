package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Dao.Request.ContactFormDto;
import org.springframework.http.ResponseEntity;

public interface ContactService {

    ResponseEntity<String> contactus(ContactFormDto contactFormDto);
}
