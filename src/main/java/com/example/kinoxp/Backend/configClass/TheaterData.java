package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.model.Theater;
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
        if (!theaterRepository.existsById(1)) {
            Theater theater1 = new Theater();
            theater1.setTheaterid(1);
            theater1.setTheaterName("BigBoi theater");
            theater1.setSeatingCapacity(400);
            theaterRepository.save(theater1);

            if (!theaterRepository.existsById(2)) {
                Theater theater2 = new Theater();
                theater2.setTheaterid(2);
                theater2.setTheaterName("SmallBoi theater");
                theater2.setSeatingCapacity(240);
                theaterRepository.save(theater2);
            }
        }
    }
}
