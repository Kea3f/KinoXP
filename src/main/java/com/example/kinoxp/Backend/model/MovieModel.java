package com.example.kinoxp.Backend.model;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;
import com.example.kinoxp.Backend.enums.TheaterEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class MovieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String movie_title;
    private int run_time;

    public Set<GenreEnum> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEnum> genres) {
        this.genres = genres;
    }

    @ElementCollection(targetClass = GenreEnum.class)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> genres;

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

    @ManyToMany
    @JoinTable(
            name = "employee_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<EmployeeModel> employees;




}