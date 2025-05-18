package com.example.besporticast.Controller.Admin.AdController;

import com.example.besporticast.DTO.Request.AdminRquest.Ad_ChapterDTO;
import com.example.besporticast.DTO.Request.AdminRquest.AudioBookDTO;
import com.example.besporticast.DTO.Request.AdminRquest.ChapterLimitRequest;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.Chapter;
import com.example.besporticast.Entity.User;
import com.example.besporticast.Service.AdminSvice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ManagerOfAdmin {

    private static final Logger logger = LoggerFactory.getLogger(ManagerOfAdmin.class);

    @Autowired
    private Ad_ListUsersService ad_ListUsersService;

    @Autowired
    private Ad_EditAudioBookService ad_EditAudioBookService;

    @Autowired
    private Ad_ListBookService ad_ListBookService;

    @Autowired
    private Ad_AddAudioBookService ad_AddAudioBookService;
    @Autowired
    private Ad_DeleteUserService ad_DeleteUserService;
    @Autowired
    private Ad_ChapterService ad_ChapterService;
    @Autowired
    private Ad_AudioBookService audioBookService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return ad_ListUsersService.getAllUsers();
    }

    @GetMapping("/audiobook")
    public List<Audiobook> adgetAllAudiobook() {
        return ad_ListBookService.AdgetAllAudiobooks();
    }

    @PostMapping("/add_audiobook")
    public ResponseEntity<List<Audiobook>> addAudioBook(@RequestBody AudioBookDTO dto) {
        logger.info(">>> Reached addAudioBook(), DTO: {}", dto);
        List<Audiobook> list = ad_AddAudioBookService.addAudioBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PutMapping("/edit_audiobook/{id}")
    public ResponseEntity<?> updateAudioBook(@PathVariable Long id, @RequestBody AudioBookDTO dto) {
        logger.info(">>> PUT /admin/edit_audiobook/{} called with DTO: {}", id, dto);
        try {
            Audiobook updateAudioBook = ad_EditAudioBookService.updateAudioBook(id, dto);
            return ResponseEntity.ok(updateAudioBook);
        } catch (RuntimeException e) {
            logger.error("Error updating audiobook ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // Trả về mã trạng thái 204 No Content khi xóa thành công
    public void deleteBook(@PathVariable Long id) {
        ad_AddAudioBookService.deleteBook(id);
    }
    @DeleteMapping("/delete_user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        ad_DeleteUserService.deleteUser(id);

    }
    @PutMapping("/{id}/chapter-limit")
    public ResponseEntity<?> setChapterLimit(
            @PathVariable("id") Integer audiobookId,
            @RequestBody ChapterLimitRequest request) {
        Audiobook updated = ad_ChapterService.setChapterLimit(audiobookId, request.getChapterLimit());
        return ResponseEntity.ok().build();
    }

    /**
     * Thêm chương mới cho audiobook, giới hạn theo chapterLimit
     */
    @PostMapping("/{audiobookId}/chapters")
    public ResponseEntity<?> addChapter(@PathVariable Integer audiobookId,
                                        @RequestBody Ad_ChapterDTO request) {
        try {
            Chapter chapter = ad_ChapterService.addChapter(
                    audiobookId,
                    request.getTitle(),
                    request.getAudioUrl(),
                    request.getDuration()
            );
            return ResponseEntity.ok("✅ Đã thêm chương: " + chapter.getTitle());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("❌ " + e.getMessage());
        }
    }
}

