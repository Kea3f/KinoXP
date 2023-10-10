package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.model.Showing;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import com.example.kinoxp.Backend.repositories.ShowingRepository;
import com.example.kinoxp.Backend.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
        // Check if the movie exists, and if not, create and save it
        if (!movieRepository.existsByMovieTitle("Independence day")) {
            Movie movie1 = new Movie();
            movie1.setMovieTitle("Independence day");
            movie1.setRuntime(120);
            movie1.setAgelimit(AgeEnum.PG13);
            movie1.setResume("The aliens are coming and\n" +
                    "their goal is to invade and\n" +
                    "destroy Earth. Fighting superior\n" +
                    "technology, mankind's best\n" +
                    "weapon is the will to survive.");

            // Create and set a Showing instance for movie1
            Showing showing1 = new Showing();
            showing1.setStartDate(LocalDate.of(2023, 10, 10)); // October 10th
            showing1.setEndDate(LocalDate.of(2023, 10, 17));   // October 17th
            showing1.setShowingDate(LocalDate.of(2023, 10, 12)); // October 12th
            showing1.setShowingTime(LocalTime.of(18, 30));

            // Save the movie1 first
            movieRepository.save(movie1);

            // Set the theater for movie1
            movie1.setTheater(theaterRepository.findById(1).orElse(null));

            // Save the movie again to update the changes
            movieRepository.save(movie1);

            // Associate the Showing with Movie and Theater
            showing1.setMovie(movie1);
            showing1.setTheater(theaterRepository.findById(1).orElse(null));

            // Save the showing to get the showingId
            showingRepository.save(showing1);

            Set<GenreEnum> genres1 = new HashSet<>();
            genres1.add(GenreEnum.ACTION);
            movie1.setGenres(genres1);

            // Save the movie again to update the changes
            movieRepository.save(movie1);
        }

    }
}
