package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.DTO.Request.AdminRquest.Ad_ChapterDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.Chapter;
import com.example.besporticast.Repository.AudiobookRepository;
import com.example.besporticast.Repository.ChapterRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
public class Ad_ChapterService {
    private final ChapterRepository chapterRepository;
    private final AudiobookRepository audiobookRepository;

    public Ad_ChapterService(ChapterRepository chapterRepo, AudiobookRepository audiobookRepo) {
        this.chapterRepository = chapterRepo;
        this.audiobookRepository = audiobookRepo;
    }

    public Chapter createChapter(Ad_ChapterDTO dto) {
        Audiobook audiobook = audiobookRepository.findById(dto.getAudiobookId())
                .orElseThrow(() -> new RuntimeException("Audiobook not found"));

        Chapter chapter = new Chapter();
        chapter.setAudiobook(audiobook);
        chapter.setTitle(dto.getTitle());
        chapter.setAudioUrl(dto.getAudioUrl());
        chapter.setDuration(dto.getDuration()); // ✅ Đảm bảo không null
        chapter.setOrder(dto.getOrder());
        chapter.setCreatedAt(Instant.now()); // ✅ Phải gán thủ công

        return chapterRepository.save(chapter);
    }

}

