package com.example.besporticast.Service.AdminSvice;


import com.example.besporticast.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Ad_DeleteUserService {
    @Autowired
   private UserRepository userRepository;

    public void deleteUser(long id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id "+ id);
        }
        userRepository.deleteById(id);

    }
}
