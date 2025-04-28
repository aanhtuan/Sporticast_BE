package com.example.besporticast.Service;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioBookService {
    @Autowired
    private AudiobookRepository repository;

    public void save(Audiobook audiobook) {
        repository.save(audiobook);
    }
    public List<Audiobook> getAllAudiobooks() {
        return repository.findAll();
    }
    public void delete(Audiobook audiobook) {
        repository.delete(audiobook);
    }
}
