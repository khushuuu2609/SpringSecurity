package com.example.SpringSecurity.Config;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Configuration
@Component
public class EmailService {
    public boolean sendEmail(String subject, String message, String to) {
        boolean f = false;
        String from = "khushboodesai2609@gmail.com";
        String host = "smtp.gmail.com";
        String username = "khushboodesai2609@gmail.com";
        String password = "sxjt ppwc jknm xerq";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            System.out.println("Sent successfully................................................................");
            f = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return f;
    }
}