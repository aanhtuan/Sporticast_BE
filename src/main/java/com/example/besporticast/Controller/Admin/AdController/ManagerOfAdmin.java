package com.example.besporticast.Controller.Admin.AdController;

import com.example.besporticast.Service.AdminSvice.Ad_AddAudioBookService;
import com.example.besporticast.Service.AdminSvice.Ad_EditAudioBookService;
import com.example.besporticast.Service.AdminSvice.Ad_ListBookService;
import com.example.besporticast.Service.AdminSvice.Ad_ListUsersService;
import com.example.besporticast.DTO.Request.AdminRquest.AudioBookDTO;
import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class ManagerOfAdmin {

    @Autowired
    Ad_ListUsersService ad_ListUsersService;

    @Autowired
    Ad_EditAudioBookService ad_EditAudioBookService;

    @Autowired
    private Ad_ListBookService ad_ListBookService;
    @Autowired
    private Ad_AddAudioBookService ad_AddAudioBookService;

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
        System.out.println(">>> Reached addAudioBook()");
        List<Audiobook> list = ad_AddAudioBookService.addAudioBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PutMapping("/edit_audiobook/{id}")
    public ResponseEntity<?> updateAudioBook(@PathVariable Long id, @RequestBody AudioBookDTO dto){

        try{
            Audiobook updateAudioBook = ad_EditAudioBookService.updateAudioBook(id, dto);
            return ResponseEntity.ok(updateAudioBook);

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }




}
