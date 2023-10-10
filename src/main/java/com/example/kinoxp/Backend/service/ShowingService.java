package com.example.kinoxp.Backend.service;

import com.example.kinoxp.Backend.model.Showing;
import com.example.kinoxp.Backend.repositories.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowingService {
    private final ShowingRepository showingRepository;

    @Autowired
    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

}

