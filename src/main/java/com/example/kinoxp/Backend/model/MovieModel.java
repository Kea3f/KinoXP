package com.example.kinoxp.Backend.model;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;
import com.example.kinoxp.Backend.enums.TheaterEnum;
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
    private GenreEnum genre;
    private AgeEnum age_limit;
    private String resume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getRun_time() {
        return run_time;
    }

    public void setRun_time(int run_time) {
        this.run_time = run_time;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public AgeEnum getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(AgeEnum age_limit) {
        this.age_limit = age_limit;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getScreening_period_in_days() {
        return screening_period_in_days;
    }

    public void setScreening_period_in_days(int screening_period_in_days) {
        this.screening_period_in_days = screening_period_in_days;
    }

    public TheaterEnum getTheater() {
        return theater;
    }

    public void setTheater(TheaterEnum theater) {
        this.theater = theater;
    }

    private int screening_period_in_days;
    private TheaterEnum theater;

    //private int cleaningTimeMinutes; er det nødvendigt???

    //@ManyToOne
    //private MovieTheater movieTheater; // The specific movie theater where the movie is shown - create enum



}

