package com.example.besporticast.Service.AdminSvice;

import com.example.besporticast.Entity.Audiobook;
import com.example.besporticast.Repository.AudiobookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ad_ListBookService {
    @Autowired
    private AudiobookRepository audiobookRepository;

    public List<Audiobook> AdgetAllAudiobooks() {

        return audiobookRepository.findAll();
    }
}
