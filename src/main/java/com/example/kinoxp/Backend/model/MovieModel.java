package com.example.kinoxp.Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movie_title;
    private int run_time;
    private String genre;
    private int age_limit;
    private String resume;
    private int screening_period_in_days;

    //private int cleaningTimeMinutes; er det nødvendigt???

    //@ManyToOne
    //private MovieTheater movieTheater; // The specific movie theater where the movie is shown - create enum



}

