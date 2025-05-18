package com.example.besporticast.Service.Mail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FptAiTTSService ttsService;

    public void sendCode(String toEmail, String code) {
        String text = "Mã xác thực của bạn là: " + code + ". Mã sẽ hết hạn sau 5 phút.";
        String filePath = "output/otp_" + System.currentTimeMillis() + ".mp3";

        try {
            // 1. Tạo file mp3 từ văn bản
            ttsService.convertTextToSpeech(text, filePath);

            // 2. Soạn nội dung HTML có nhúng player
            String html = "<html>\n" +
                    "<head>\n" +
                    "  <style>\n" +
                    "    body {\n" +
                    "      font-family: Arial, sans-serif;\n" +
                    "      background-color: #f9f9f9;\n" +
                    "      color: #333333;\n" +
                    "      margin: 0;\n" +
                    "      padding: 0;\n" +
                    "    }\n" +
                    "    .container {\n" +
                    "      max-width: 400px;\n" +
                    "      margin: 30px auto;\n" +
                    "      background-color: #ffffff;\n" +
                    "      border: 1px solid #ddd;\n" +
                    "      border-radius: 8px;\n" +
                    "      padding: 20px;\n" +
                    "      box-shadow: 0 2px 8px rgba(0,0,0,0.1);\n" +
                    "      text-align: center;\n" +
                    "    }\n" +
                    "    h2 {\n" +
                    "      color: #2c3e50;\n" +
                    "      margin-bottom: 15px;\n" +
                    "    }\n" +
                    "    p {\n" +
                    "      font-size: 16px;\n" +
                    "      margin: 10px 0;\n" +
                    "    }\n" +
                    "    strong {\n" +
                    "      font-size: 24px;\n" +
                    "      color: #e74c3c;\n" +
                    "      letter-spacing: 3px;\n" +
                    "    }\n" +
                    "    audio {\n" +
                    "      margin-top: 20px;\n" +
                    "      width: 100%;\n" +
                    "      outline: none;\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <div class=\"container\">\n" +
                    "    <h2>Mã xác thực của bạn:</h2>\n" +
                    "    <p><strong>" + code + "</strong></p>\n" +
                    "    <p>Mã sẽ hết hạn sau 5 phút.</p>\n" +
                    "    <p>Nghe mã xác thực:</p>\n" +
                    "    <audio controls>\n" +
                    "      <source src='cid:otpAudio' type='audio/mpeg'>\n" +
                    "      Trình duyệt của bạn không hỗ trợ phát âm thanh.\n" +
                    "    </audio>\n" +
                    "  </div>\n" +
                    "</body>\n" +
                    "</html>";


            // 3. Tạo MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("Mã xác thực đăng nhập");
            helper.setText(html, true); // enable HTML

            FileSystemResource mp3 = new FileSystemResource(new File(filePath));

            // Nhúng file mp3 vào player
            helper.addInline("otpAudio", mp3, "audio/mpeg");

            // Đính kèm file mp3
            helper.addAttachment("ma-xac-thuc.mp3", mp3);

            // Gửi email
            mailSender.send(message);

        } catch (MessagingException | IOException | InterruptedException e) {
            throw new RuntimeException("Lỗi gửi email: " + e.getMessage(), e);
        }
    }
}

