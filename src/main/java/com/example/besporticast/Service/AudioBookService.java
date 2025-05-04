package com.example.besporticast.Service;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@Data
public class AudioBookService {
    @Autowired
    private AudiobookRepository audiobookRepository;

    public void save(Audiobook audiobook) {

        audiobookRepository.save(audiobook);
    }
    public List<Audiobook> getAllAudiobooks() {

        return audiobookRepository.findAll();
    }
    public void delete(Audiobook audiobook) {

        audiobookRepository.delete(audiobook);
    }
    public List<Audiobook> searchBooks(String query){
        return audiobookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query,query);
    }

}
