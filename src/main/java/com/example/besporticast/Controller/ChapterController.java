package com.example.besporticast.Controller;

import com.example.besporticast.DTO.Request.ChapterDTO;
import com.example.besporticast.Entity.Chapter;
import com.example.besporticast.Service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @GetMapping("/{audiobookId}/chapters")
    public ResponseEntity<List<ChapterDTO>> getChaptersByAudiobookId(@PathVariable Integer audiobookId) {
        return ResponseEntity.ok(chapterService.getChaptersByAudiobookId(audiobookId));
    }

    @GetMapping("/chapters/{chapterId}")
    public ResponseEntity<ChapterDTO> getChapterById(@PathVariable Integer chapterId) {
        return chapterService.getChapterById(chapterId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
