package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.Chapter;
import com.example.besporticast.Repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class Ad_ChapterService {

    private final ChapterRepository chapterRepository;
    private final Ad_AudioBookService audiobookService;

    // Thêm một chương mới cho audiobook có kiểm tra giới hạn
    public Chapter addChapter(Integer audiobookId, String title, String audioUrl, Integer duration) {
        Audiobook audiobook = audiobookService.getAudiobookById(audiobookId);

        int currentCount = chapterRepository.countByAudiobookId(audiobookId);
        Integer limit = audiobook.getChapterLimit();

        if (limit != null && currentCount >= limit) {
            throw new IllegalStateException("Đã đạt giới hạn số chương");
        }

        Chapter chapter = new Chapter();
        chapter.setAudiobook(audiobook);
        chapter.setTitle(title);
        chapter.setAudioUrl(audioUrl);
        chapter.setDuration(duration);
        chapter.setOrder(currentCount + 1);
        chapter.setCreatedAt(Instant.now());

        return chapterRepository.save(chapter);
    }

    // Lấy số trang giới hạn hiện tại của audio book
    public int getChapterLimit(Integer audiobookId) {
        Audiobook audiobook = audiobookService.getAudiobookById(audiobookId);
        return audiobook.getChapterLimit();
    }

    // Lấy số lượng chương hiện tại của audiobook
    public int getChapterCount(Integer audiobookId) {
        return chapterRepository.countByAudiobookId(audiobookId);
    }

    // Đặt giới hạn số chương cho audiobook
    public Audiobook setChapterLimit(Integer audiobookId, Integer chapterLimit) {
        Audiobook audiobook = audiobookService.getAudiobookById(audiobookId);
        audiobook.setChapterLimit(chapterLimit);
        return audiobookService.save(audiobook); // gọi đúng service
    }
}
