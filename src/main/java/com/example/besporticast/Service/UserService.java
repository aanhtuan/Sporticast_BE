package com.example.besporticast.Service;

import com.example.besporticast.Entity.User;
import com.example.besporticast.Repository.UserRepository;
import com.example.besporticast.Util.JwtUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwtUtil;


    public String registerUser(String name, String email, String password) {


        if (userRepository.existsByEmail(email)) {
            return "Email Already Exists";
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAvatar(getRandomAvatarUrl());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
        return "User created successfully";

    }

    private String getRandomAvatarUrl() {
        int index = new Random().nextInt(70) + 1; // tạo số từ 1 đến 70
        return "https://i.pravatar.cc/150?img=" + index;
    }


    public Map<String, Object> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                Map<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("is_admin", user.getIs_admin());
                data.put("user_id", user.getId());
                return data;
            }
        }
        return null;
    }

}


