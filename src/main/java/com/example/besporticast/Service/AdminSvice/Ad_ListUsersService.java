package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.Entity.User;
import com.example.besporticast.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ad_ListUsersService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
