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
            message.setFrom("donipatipoojitha@gmail.com");
            message.setTo(toEmail);
            message.setSubject("Citizen Connect OTP Verification");
            message.setText(
                "Hi " + username + ",\n\n" +
                "Your Citizen Connect OTP is: " + otp + "\n\n" +
                "This OTP is valid for 5 minutes.\n\n" +
                "Regards,\nCitizen Connect Team"
            );

            mailSender.send(message);
            System.out.println("OTP email sent to: " + toEmail);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("OTP email sending failed: " + e.getMessage());
        }
    }

    public boolean sendLoginEmail(String toEmail, String username) {
        return true;
    }
}
