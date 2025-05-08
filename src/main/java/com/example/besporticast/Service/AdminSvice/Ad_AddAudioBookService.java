package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.DTO.Request.AdminRquest.AudioBookDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ad_AddAudioBookService  {
    @Autowired
    private AudiobookRepository audiobookRepository;

    public List<Audiobook> addAudioBook(AudioBookDTO bookDTO) {
        Audiobook audiobook = new Audiobook();

        audiobook.setAuthor(bookDTO.getAuthor());
        audiobook.setTitle(bookDTO.getTitle());
        audiobook.setDescription(bookDTO.getDescription());
        audiobook.setLanguage(bookDTO.getLanguage());
        audiobook.setCategory(bookDTO.getCategory());
        audiobook.setRating(bookDTO.getRating());
        audiobook.setListenCount(bookDTO.getListenCount());
        audiobook.setAudioUrl(bookDTO.getAudioUrl());
        audiobook.setImageUrl(bookDTO.getImageUrl());
        audiobook.setDuration(bookDTO.getDuration());

        audiobookRepository.save(audiobook);

        return audiobookRepository.findAll();
    }
}
