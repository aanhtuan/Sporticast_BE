package com.example.besporticast.Repository;

import com.example.besporticast.DTO.Request.ChapterDTO;
import com.example.besporticast.Entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
     List<Chapter> findByAudiobookIdOrderByOrderAsc (Integer audiobookId);
}
