package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.model.TheaterModel;
import com.example.kinoxp.Backend.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TheaterData implements CommandLineRunner {

    @Autowired
    TheaterRepository theaterRepository;

    @Override
    public void run(String... args) throws Exception {
        TheaterModel theater1 = new TheaterModel();
        theater1.setId(1);
        theater1.setTheaterName("BigBoi theater");
        theater1.setSeatingCapacity(400);
        theaterRepository.save(theater1);


        TheaterModel theater2 = new TheaterModel();
        theater2.setId(2);
        theater2.setTheaterName("SmallBoi theater");
        theater2.setSeatingCapacity(240);
        theaterRepository.save(theater2);
    }
}
