package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.User;
import com.example.besporticast.Repository.AudiobookRepository;
import com.example.besporticast.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class Ad_AddFavouriteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AudiobookRepository audiobookRepository;

    public void addFavourite(Long user_id, Long audiobook_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Audiobook audiobook = audiobookRepository.findById(audiobook_id)
                .orElseThrow(() -> new RuntimeException("Book not found"));


        user.getFavourites().add(audiobook);
        userRepository.save(user);
    }
    public Set<Audiobook> getFavoriteBooks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavourites();
    }


}
