package com.example.kinoxp.Backend.service;

import com.example.kinoxp.Backend.dto.MovieDto;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<String> searchMoviesByTitle(String query) {
        List<Movie> matchingMovies = movieRepository.findByMovieTitleContainingIgnoreCase(query);
        return matchingMovies.stream()
                .map(Movie::getMovieTitle)
                .collect(Collectors.toList());
    }

    public MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getMovieTitle());
        movieDto.setRuntime(movie.getRuntime());
        movieDto.setAgeLimit(movie.getAgelimit());
        movieDto.setResume(movie.getResume());
        movieDto.setTheater(movie.getTheater()); // Use parentheses to call the setter
        return movieDto;
    }

}
