package com.example.kinoxp.Backend.dto;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;
import com.example.kinoxp.Backend.model.Theater;

import java.time.LocalDate;
import java.util.Set;

public class MovieDto {
    private String title;
    private int movieid;
    private int runtime;
    private AgeEnum ageLimit;
    private String resume;
    private Set<GenreEnum> genres;
    private Theater theater;

    private LocalDate showingDate;

    public LocalDate getShowingDate() {
        return showingDate;
    }

    public void setShowingDate(LocalDate showingDate) {
        this.showingDate = showingDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public AgeEnum getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(AgeEnum ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Set<GenreEnum> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEnum> genres) {
        this.genres = genres;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }
}
