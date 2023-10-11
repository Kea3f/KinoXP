package com.example.kinoxp.Backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.kinoxp.Backend.dto.MovieDto;
import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.model.Theater;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchMoviesByTitle() {
        // Define your test data
        String query = "Star";
        List<Movie> mockMovies = Arrays.asList(
                new Movie(),
                new Movie(),
                new Movie()
        );

        // Set the movieTitle property for each Movie object
        mockMovies.get(0).setMovieTitle("Star Wars");
        mockMovies.get(1).setMovieTitle("Star Trek");
        mockMovies.get(2).setMovieTitle("Avatar");

        // Mock the behavior of movieRepository.findByMovieTitle
        when(movieRepository.findByMovieTitleContainingIgnoreCase(query)).thenReturn(mockMovies);

        // Call the method to be tested
        List<String> result = movieService.searchMoviesByTitle(query);

        // Verify the result
        assertEquals(Arrays.asList("Star Wars", "Star Trek", "Avatar"), result);

        // Verify that the movieRepository method was called with the correct argument
        verify(movieRepository).findByMovieTitleContainingIgnoreCase(query);
    }

    @Test
    public void testConvertToDto() {
        // Arrange
        Movie movie = new Movie();
        movie.setMovieTitle("SampleMovieTitle");
        movie.setRuntime(120);
        movie.setAgelimit(AgeEnum.PG);
        movie.setResume("Sample movie description");

        Theater theater = new Theater();
        theater.setTheaterName("SampleTheater");
        movie.setTheater(theater);

        // Act
        MovieDto movieDto = movieService.convertToDto(movie);

        // Assert
        assertEquals(movie.getMovieTitle(), movieDto.getTitle());
        assertEquals(movie.getRuntime(), movieDto.getRuntime());
        assertEquals(movie.getAgelimit(), movieDto.getAgeLimit());
        assertEquals(movie.getResume(), movieDto.getResume());
    }

}





