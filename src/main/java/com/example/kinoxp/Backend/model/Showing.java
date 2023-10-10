package com.example.kinoxp.Backend.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showingid;
    private LocalDate startDate;
    private LocalDate endDate;

    @Column(name = "showing_date")
    private LocalDate showingDate;

    private LocalTime showingTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theaterid") // The name of the foreign key column in the movie table
    private Theater theater;

    public Theater getTheater() {
        return theater;
    }
    

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getShowingid() {
        return showingid;
    }

    public void setShowingid(int showing_id) {
        this.showingid = showing_id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getShowingDate() {
        return showingDate;
    }

    public void setShowingDate(LocalDate showingDate) {
        this.showingDate = showingDate;
    }

    public LocalTime getShowingTime() {
        return showingTime;
    }

    public void setShowingTime(LocalTime showingTime) {
        this.showingTime = showingTime;
    }

    public void setTheaters(Set<Theater> theaters) {
    }
}
