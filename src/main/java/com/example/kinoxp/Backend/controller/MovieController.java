package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.repositories.MovieRepository;
import com.example.kinoxp.Backend.repositories.TheaterRepository;
import com.example.kinoxp.Backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {


    private final MovieService movieService;
    private final TheaterRepository theaterRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieService movieService, TheaterRepository theaterRepository, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.theaterRepository = theaterRepository;
        this.movieRepository = movieRepository;

    }

    @GetMapping("/moviecalendar")
    public String showMovieCalendar() {
        return "movieCalendar";
    }
}




