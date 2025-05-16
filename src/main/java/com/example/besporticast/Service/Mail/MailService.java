package com.example.besporticast.Service.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực đăng nhập");
        message.setText("Mã xác thực của bạn là: " + code + "\n"+
                "Mã sẽ hết hạn sau 5 phút.");
        mailSender.send(message);
    }
}
