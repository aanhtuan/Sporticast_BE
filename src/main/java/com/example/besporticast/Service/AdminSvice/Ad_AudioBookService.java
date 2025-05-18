package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ad_AudioBookService {
    @Autowired
    AudiobookRepository audiobookRepository;
    public Audiobook getAudiobookById(Integer id) {
        return audiobookRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Không tìm thấy audiobook"));
    }

    public Audiobook setChapterLimit(Integer id, Integer limit) {
        Audiobook audiobook = getAudiobookById(id);
        audiobook.setChapterLimit(limit);
        return audiobookRepository.save(audiobook);
    }
    public Audiobook save(Audiobook audiobook) {
        return audiobookRepository.save(audiobook);
    }

}
