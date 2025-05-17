package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.DTO.Request.AdminRquest.AudioBookDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import com.example.besporticast.Repository.FavoriteRepository;
import com.example.besporticast.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Ad_AddAudioBookService  {
    @Autowired
    private AudiobookRepository audiobookRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
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

    @Transactional
    public void deleteBook(Long bookId) {
        Audiobook audiobook = audiobookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Audiobook not found"));

        // Xoá tất cả user-favourite liên quan
        userRepository.removeAllFavouritesWithBookId(bookId); // viết custom query

        // Bây giờ có thể xoá audiobook
        audiobookRepository.delete(audiobook);
    }

}
