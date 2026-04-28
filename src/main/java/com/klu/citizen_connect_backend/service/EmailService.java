package com.klu.citizen_connect_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendOtpEmail(String toEmail, String username, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Citizen Connect - OTP Verification");
            message.setText(
                    "Hello " + username + ",\n\n" +
                    "Your OTP for Citizen Connect registration is: " + otp + "\n\n" +
                    "This OTP is valid for 5 minutes.\n\n" +
                    "Regards,\nCitizen Connect Team"
            );

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            System.out.println("OTP MAIL ERROR: " + e.getMessage());
            return false;
        }
    }

    public boolean sendLoginEmail(String toEmail, String username) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Citizen Connect - Login Alert");
            message.setText(
                    "Hello " + username + ",\n\n" +
                    "Your account was logged in successfully.\n\n" +
                    "If this was not you, please reset your password immediately.\n\n" +
                    "Regards,\nCitizen Connect Team"
            );

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            System.out.println("LOGIN MAIL ERROR: " + e.getMessage());
            return false;
        }
    }
}
