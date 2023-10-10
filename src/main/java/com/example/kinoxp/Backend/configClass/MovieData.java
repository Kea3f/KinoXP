package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.model.Showing;
import com.example.kinoxp.Backend.model.Theater;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import com.example.kinoxp.Backend.repositories.ShowingRepository;
import com.example.kinoxp.Backend.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Component
public class MovieData implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private TheaterRepository theaterRepository;

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

            // Check if the movie exists, and if not, create and save it
            if (!movieRepository.existsByMovieTitle("Independence day")) {
                // Create a Movie instance
                Movie movie1 = new Movie();
                movie1.setMovieTitle("Independence day");
                movie1.setRuntime(120);
                movie1.setAgelimit(AgeEnum.PG13);
                movie1.setResume("The aliens are coming and\n" +
                        "their goal is to invade and\n" +
                        "destroy Earth. Fighting superior\n" +
                        "technology, mankind's best\n" +
                        "weapon is the will to survive.");

                // Set the theaters for movie1
                movie1.setTheaters(Arrays.asList(theater1));


                // Create a Showing instance
                Showing showing1 = new Showing();
                showing1.setStartDate(LocalDate.of(2023, 10, 10)); // October 10th
                showing1.setEndDate(LocalDate.of(2023, 10, 17));   // October 17th
                showing1.setShowingDate(LocalDate.of(2023, 10, 12)); // October 12th
                showing1.setShowingTime(LocalTime.of(18, 30));

                // Set the relationships using JPA annotations
                showing1.setMovie(movie1); // This establishes the many-to-one relationship
                showing1.setTheater(theater1); // This establishes the many-to-one relationship

                // Save the entities
                movieRepository.save(movie1);
                showingRepository.save(showing1);
                theaterRepository.save(theater1);
            }
        }
    }
}




