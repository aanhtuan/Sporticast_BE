package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.DTO.Request.AdminRquest.AudioBookDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class Ad_EditAudioBookService {
    @Autowired
    private AudiobookRepository audiobookRepository;

    public Audiobook updateAudioBook(Long  id, AudioBookDTO dto) {
        Audiobook existingBook = audiobookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

         existingBook.setTitle(dto.getTitle());
         existingBook.setAuthor(dto.getAuthor());
         existingBook.setDescription(dto.getDescription());
         existingBook.setImageUrl(dto.getImageUrl());
         existingBook.setAudioUrl(dto.getAudioUrl());
         existingBook.setDuration(dto.getDuration());
         existingBook.setCategory(dto.getCategory());
         existingBook.setListenCount(dto.getListenCount());
         existingBook.setRating(dto.getRating());
         return audiobookRepository.save(existingBook);

    }



}
