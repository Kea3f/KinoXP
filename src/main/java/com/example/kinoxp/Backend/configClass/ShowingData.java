package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.repositories.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ShowingData implements CommandLineRunner {
    @Autowired
    ShowingRepository showingRepository;
    @Override
    public void run(String... args) throws Exception {








    }
}
