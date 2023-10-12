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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Component
@Order(1)
public class MovieData implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create a Theater instance for "Independence Day"
        Theater theater1 = new Theater();
        theater1.setTheaterName("BigBoi theater");
        theater1.setSeatingCapacity(400);
        theaterRepository.save(theater1);

// Check if the movie exists, and if not, create and save it
        if (!movieRepository.existsByMovieTitle("Independence day")) {
            // Create a Movie instance for "Independence Day"
            Movie movie1 = new Movie();
            movie1.setMovieid(1);
            movie1.setMovieTitle("Independence day");
            movie1.setRuntime(120);
            movie1.setAgelimit(AgeEnum.PG13);
            movie1.setResume("The aliens are coming and\n" +
                    "their goal is to invade and\n" +
                    "destroy Earth. Fighting superior\n" +
                    "technology, mankind's best\n" +
                    "weapon is the will to survive.");

            movieRepository.save(movie1);
            // Set the theaters for movie1
            movie1.setTheaters(Arrays.asList(theater1));

            // Create a Showing instance
            Showing showing1 = new Showing();
            showing1.setStartDate(LocalDate.of(2023, 10, 10));
            showing1.setEndDate(LocalDate.of(2023, 10, 17));
            showing1.setShowingDate(LocalDate.of(2023, 10, 12));
            showing1.setShowingTime(LocalTime.of(18, 30));

            // Set the relationships using JPA annotations
            showing1.setMovie(movie1);
            showing1.setTheater(theater1);

            // Save the entities
            movieRepository.save(movie1);
            showingRepository.save(showing1);
        }

// Create a Theater instance for "Halloween Ends"
        Theater theater2 = new Theater();
        theater2.setTheaterName("SmallBoi theater");
        theater2.setSeatingCapacity(240);
        theaterRepository.save(theater2);

// Check if the movie exists, and if not, create and save it
        if (!movieRepository.existsByMovieTitle("Halloween ends")) {
            // Create a Movie instance for "Halloween Ends"
            Movie movie2 = new Movie();
            movie2.setMovieid(2);
            movie2.setMovieTitle("Halloween ends");
            movie2.setRuntime(120);
            movie2.setAgelimit(AgeEnum.PG13);
            movie2.setResume("A different movie description for Halloween Ends.");

            movieRepository.save(movie2);
            // Set the theaters for movie2
            movie2.setTheaters(Arrays.asList(theater2));

            // Create a Showing instance
            Showing showing2 = new Showing();
            showing2.setStartDate(LocalDate.of(2023, 10, 10));
            showing2.setEndDate(LocalDate.of(2023, 11, 17));
            showing2.setShowingDate(LocalDate.of(2023, 10, 15));
            showing2.setShowingTime(LocalTime.of(20, 30));

            // Set the relationships using JPA annotations
            showing2.setMovie(movie2);
            showing2.setTheater(theater2);

            // Save the entities
            movieRepository.save(movie2);
            showingRepository.save(showing2);
        }

    }
}




