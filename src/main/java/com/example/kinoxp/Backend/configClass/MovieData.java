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
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieData implements CommandLineRunner {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowingRepository showingRepository;

    @Override
    public void run(String... args) throws Exception {
        // Creating the two theaters
        if (!theaterRepository.existsById(1)) {
            Theater theater1 = new Theater();
            theater1.setId(1);
            theater1.setTheaterName("BigBoi Theater");
            theater1.setSeatingCapacity(400);
            theaterRepository.save(theater1);
        }

        if (!theaterRepository.existsById(2)) {
            Theater theater2 = new Theater();
            theater2.setId(2);
            theater2.setTheaterName("SmallBoi Theater");
            theater2.setSeatingCapacity(240);
            theaterRepository.save(theater2);
        }

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

        // Check if movie exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("Avengers")) {
            Movie movie2 = new Movie();
            movie2.setMovieTitle("Avengers");
            movie2.setRuntime(130);
            movie2.setAgelimit(AgeEnum.PG13);
            movie2.setResume("In the epic conclusion to the Infinity Saga,\n" +
                    "the Avengers must band together to undo\n" +
                    "the damage caused by Thanos and\n" +
                    "save the universe. This thrilling superhero film features\n" +
                    "a star-studded cast and incredible action sequences.");

            // Create and set a Showing instance for movie2
            Showing showing2 = new Showing();
            showing2.setStartDate(LocalDate.of(2023, 10, 11)); // October 10th
            showing2.setEndDate(LocalDate.of(2023, 10, 17));   // October 17th
            showing2.setShowingDate(LocalDate.of(2023, 10, 12)); // October 12th
            showing2.setShowingTime(LocalTime.of(18, 30));

            // Set the theater for movie2
            Theater theater2 = theaterRepository.findById(1).orElse(null); // Change to the appropriate theater
            movie2.setTheater(theater2);

            // Associate the Showing with Movie and Theater
            showing2.setMovie(movie2);
            showing2.setTheater(theater2);

            // Save the movie2 first
            movieRepository.save(movie2);

            // Save the showing2 to get the showingId
            showingRepository.save(showing2);

            Set<GenreEnum> genres2 = new HashSet<>();
            genres2.add(GenreEnum.ACTION);
            movie2.setGenres(genres2);

            // Save the movie2 again to update the changes
            movieRepository.save(movie2);
        }
        // Check if movie "Barbie" exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("Barbie")) {
            Movie movieBarbie = new Movie();
            movieBarbie.setMovieTitle("Barbie");
            movieBarbie.setRuntime(100);
            movieBarbie.setAgelimit(AgeEnum.G);
            movieBarbie.setResume("Join Barbie in her exciting adventures\n" +
                    "in the world of fashion and glamour.");

            // Create and set a Showing instance for "Barbie" movie
            Showing showingBarbie = new Showing();
            showingBarbie.setStartDate(LocalDate.now());
            showingBarbie.setEndDate(LocalDate.now().plusDays(7));
            showingBarbie.setShowingDate(LocalDate.now().plusDays(2));
            showingBarbie.setShowingTime(LocalTime.of(15, 45));

            // Set the theater for "Barbie" movie (Theater 2)
            Theater theater2 = theaterRepository.findById(2).orElse(null);
            movieBarbie.setTheater(theater2);

            // Associate the Showing with "Barbie" movie and Theater 2
            showingBarbie.setMovie(movieBarbie);
            showingBarbie.setTheater(theater2);

            // Save the "Barbie" movie
            movieRepository.save(movieBarbie);

            // Save the showing for "Barbie" to get the showingId
            showingRepository.save(showingBarbie);

            Set<GenreEnum> genresBarbie = new HashSet<>();
            genresBarbie.add(GenreEnum.FAMILY);
            movieBarbie.setGenres(genresBarbie);

            // Save the "Barbie" movie again to update the changes
            movieRepository.save(movieBarbie);
        }



// Check if movie "Halloween Ends" exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("Halloween Ends")) {
            Movie movieHalloweenEnds = new Movie();
            movieHalloweenEnds.setMovieTitle("Halloween Ends");
            movieHalloweenEnds.setRuntime(120);
            movieHalloweenEnds.setAgelimit(AgeEnum.R);
            movieHalloweenEnds.setResume("The thrilling conclusion to the Halloween saga, " +
                    "as Michael Myers returns for a final showdown.");

            // Create and set a Showing instance for "Halloween Ends" movie
            Showing showingHalloweenEnds = new Showing();
            showingHalloweenEnds.setStartDate(LocalDate.now());
            showingHalloweenEnds.setEndDate(LocalDate.now().plusDays(7));
            showingHalloweenEnds.setShowingDate(LocalDate.now().plusDays(2));
            showingHalloweenEnds.setShowingTime(LocalTime.of(20, 0));

            // Set the theater for "Halloween Ends" movie (Theater 1)
            Theater theater1 = theaterRepository.findById(1).orElse(null);
            movieHalloweenEnds.setTheater(theater1);

            // Associate the Showing with "Halloween Ends" movie and Theater 1
            showingHalloweenEnds.setMovie(movieHalloweenEnds);
            showingHalloweenEnds.setTheater(theater1);

            // Save the "Halloween Ends" movie
            movieRepository.save(movieHalloweenEnds);

            // Save the showing for "Halloween Ends" to get the showingId
            showingRepository.save(showingHalloweenEnds);

            Set<GenreEnum> genresHalloweenEnds = new HashSet<>();
            genresHalloweenEnds.add(GenreEnum.HORROR);
            movieHalloweenEnds.setGenres(genresHalloweenEnds);

            // Save the "Halloween Ends" movie again to update the changes
            movieRepository.save(movieHalloweenEnds);
        }
// Check if movie "The Matrix" exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("The Matrix")) {
            Movie movieMatrix = new Movie();
            movieMatrix.setMovieTitle("The Matrix");
            movieMatrix.setRuntime(136);
            movieMatrix.setAgelimit(AgeEnum.R);
            movieMatrix.setResume("A computer programmer discovers that reality " +
                    "as he knows it is a simulation created by machines.");

            // Create and set a Showing instance for "The Matrix" movie
            Showing showingMatrix = new Showing();
            showingMatrix.setStartDate(LocalDate.now());
            showingMatrix.setEndDate(LocalDate.now().plusDays(7));
            showingMatrix.setShowingDate(LocalDate.now().plusDays(2));
            showingMatrix.setShowingTime(LocalTime.of(21, 0));

            // Retrieve the theater for "Barbie" movie (Theater 2)
            Theater theater2 = theaterRepository.findById(2).orElse(null);

            // Set the theater for "The Matrix" movie (Theater 1)
            movieMatrix.setTheater(theater2);

            // Associate the Showing with "The Matrix" movie and Theater 1
            showingMatrix.setMovie(movieMatrix);
            showingMatrix.setTheater(theater2);

            // Save the "The Matrix" movie
            movieRepository.save(movieMatrix);

            // Save the showing for "The Matrix" to get the showingId
            showingRepository.save(showingMatrix);

            Set<GenreEnum> genresMatrix = new HashSet<>();
            genresMatrix.add(GenreEnum.SCI_FI);
            genresMatrix.add(GenreEnum.ACTION);
            movieMatrix.setGenres(genresMatrix);

            // Save the "The Matrix" movie again to update the changes
            movieRepository.save(movieMatrix);
        }

// Check if movie "Titanic" exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("Titanic")) {
            Movie movieTitanic = new Movie();
            movieTitanic.setMovieTitle("Titanic");
            movieTitanic.setRuntime(195);
            movieTitanic.setAgelimit(AgeEnum.PG13);
            movieTitanic.setResume("A love story set against the backdrop of the ill-fated " +
                    "maiden voyage of the RMS Titanic.");

            // Create and set a Showing instance for "Titanic" movie
            Showing showingTitanic = new Showing();
            showingTitanic.setStartDate(LocalDate.now());
            showingTitanic.setEndDate(LocalDate.now().plusDays(7));
            showingTitanic.setShowingDate(LocalDate.now().plusDays(2));
            showingTitanic.setShowingTime(LocalTime.of(19, 30));

            // Retrieve the theater for "Barbie" movie (Theater 2)
            Theater theater2 = theaterRepository.findById(2).orElse(null);

            // Set the theater for "Titanic" movie (Theater 2)
            movieTitanic.setTheater(theater2);

            // Associate the Showing with "Titanic" movie and Theater 2
            showingTitanic.setMovie(movieTitanic);
            showingTitanic.setTheater(theater2);

            // Save the "Titanic" movie
            movieRepository.save(movieTitanic);

            // Save the showing for "Titanic" to get the showingId
            showingRepository.save(showingTitanic);

            Set<GenreEnum> genresTitanic = new HashSet<>();
            genresTitanic.add(GenreEnum.ROMANCE);
            genresTitanic.add(GenreEnum.DRAMA);
            movieTitanic.setGenres(genresTitanic);

            // Save the "Titanic" movie again to update the changes
            movieRepository.save(movieTitanic);
        }

// Check if movie "Jurassic Park" exists, if not, create and save it
        if (!movieRepository.existsByMovieTitle("Jurassic Park")) {
            Movie movieJurassicPark = new Movie();
            movieJurassicPark.setMovieTitle("Jurassic Park");
            movieJurassicPark.setRuntime(127);
            movieJurassicPark.setAgelimit(AgeEnum.PG);
            movieJurassicPark.setResume("A wealthy entrepreneur secretly creates a theme park " +
                    "featuring living dinosaurs, but chaos erupts when the dinosaurs escape.");

            // Create and set a Showing instance for "Jurassic Park" movie
            Showing showingJurassicPark = new Showing();
            showingJurassicPark.setStartDate(LocalDate.now());
            showingJurassicPark.setEndDate(LocalDate.now().plusDays(7));
            showingJurassicPark.setShowingDate(LocalDate.now().plusDays(2));
            showingJurassicPark.setShowingTime(LocalTime.of(16, 45));

            // Retrieve the theater for "Barbie" movie (Theater 2)
            Theater theater1 = theaterRepository.findById(1).orElse(null);

            // Set the theater for "Jurassic Park" movie (Theater 1)
            movieJurassicPark.setTheater(theater1);

            // Associate the Showing with "Jurassic Park" movie and Theater 1
            showingJurassicPark.setMovie(movieJurassicPark);
            showingJurassicPark.setTheater(theater1);

            // Save the "Jurassic Park" movie
            movieRepository.save(movieJurassicPark);

            // Save the showing for "Jurassic Park" to get the showingId
            showingRepository.save(showingJurassicPark);

            Set<GenreEnum> genresJurassicPark = new HashSet<>();
            genresJurassicPark.add(GenreEnum.ACTION);
            genresJurassicPark.add(GenreEnum.ADVENTURE);
            movieJurassicPark.setGenres(genresJurassicPark);

            // Save the "Jurassic Park" movie again to update the changes
            movieRepository.save(movieJurassicPark);
        }
    }
}
