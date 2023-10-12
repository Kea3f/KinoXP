package com.example.kinoxp.Backend.repositories;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByMovieTitleContainingIgnoreCase(String query);

    Movie findDetailsByMovieTitle(String title);

    boolean existsByMovieTitle(String s);


}
