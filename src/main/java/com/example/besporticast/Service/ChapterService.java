package com.example.besporticast.Service;

import com.example.besporticast.DTO.Request.ChapterDTO;
import com.example.besporticast.Entity.Chapter;
import com.example.besporticast.Repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;
    public List<ChapterDTO> getChaptersByAudiobookId(Integer audiobookId) {
        return chapterRepository.findByAudiobookIdOrderByOrderAsc(audiobookId)
                .stream()
                .map(ChapterDTO::new)
                .collect(Collectors.toList());
    }


    public Optional<ChapterDTO> getChapterById(Integer id) {
        return chapterRepository.findById(id)
                .map(ChapterDTO::new);
    }
}
