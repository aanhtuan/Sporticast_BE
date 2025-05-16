package com.example.besporticast.Service;

import com.example.besporticast.Service.Mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private static class CodeEntry {
        String code;
        long expireAt; // thời gian hết hạn (milliseconds)

        CodeEntry(String code, long expireAt) {
            this.code = code;
            this.expireAt = expireAt;
        }
    }

    private final Map<String, CodeEntry> codeStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    @Autowired
    private MailService mailService;

    public String sendVerificationCode(String email) {
        String code = String.format("%05d", random.nextInt(100000));
        long expireTime = System.currentTimeMillis() + (5 * 60 * 1000); // 5 phút

        codeStore.put(email, new CodeEntry(code, expireTime));
        mailService.sendCode(email, code);

        return "verify"; // tell frontend to go to verify page
    }

    public boolean verifyCode(String email, String code) {
        CodeEntry entry = codeStore.get(email);

        if (entry == null) return false;

        if (System.currentTimeMillis() > entry.expireAt) {
            codeStore.remove(email); // hết hạn thì xóa
            return false;
        }

        boolean isMatch = entry.code.equals(code);
        if (isMatch) {
            codeStore.remove(email); // đúng thì cũng xóa
        }

        return isMatch;
    }
}
