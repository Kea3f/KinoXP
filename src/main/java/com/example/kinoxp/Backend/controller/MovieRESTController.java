package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.dto.CalendarDto;
import com.example.kinoxp.Backend.dto.MovieDto;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.model.Showing;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import com.example.kinoxp.Backend.repositories.ShowingRepository;
import com.example.kinoxp.Backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/movies")
public class MovieRESTController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final ShowingRepository showingRepository;

    @Autowired
    public MovieRESTController(
            MovieService movieService,
            MovieRepository movieRepository,
            ShowingRepository showingRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.showingRepository = showingRepository; // Add this line to inject showingRepository
    }
    // Search movies by title endpoint
    @GetMapping("/search")
    public ResponseEntity<List<String>> searchMoviesByTitle(@RequestParam("query") String query) {
        List<Movie> matchingMovies = movieRepository.findByMovieTitleContainingIgnoreCase(query);
        List<String> matchingTitles = matchingMovies.stream()
                .map(Movie::getMovieTitle)
                .collect(Collectors.toList());
        return new ResponseEntity<>(matchingTitles, HttpStatus.OK);
    }
    @GetMapping("/details")
    public ResponseEntity<MovieDto> getMovieDetails(@RequestParam("title") String title) {
        Movie movie = movieRepository.findDetailsByMovieTitle(title);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MovieDto movieDto = movieService.convertToDto(movie);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<List<CalendarDto>> getCalendarEvents() {
        List<Showing> movieShowings = showingRepository.findAll();
        List<CalendarDto> calendarEvents = new ArrayList<>();

        // Loop through movie showings and create CalendarDto objects
        for (Showing showing : movieShowings) {
            CalendarDto calendarDto = new CalendarDto();
            calendarDto.setTitle(showing.getMovie().getMovieTitle()); // Set the movie title
            calendarDto.setShowingDate(showing.getShowingDate()); // Set the showing date
            calendarDto.setShowingTime(showing.getShowingTime()); // Set the showing time
            calendarEvents.add(calendarDto);
        }
        return new ResponseEntity<>(calendarEvents, HttpStatus.OK);
    }
}
