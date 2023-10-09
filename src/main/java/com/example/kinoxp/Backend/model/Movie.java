package com.example.kinoxp.Backend.model;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieid;
    private String movieTitle;
    private int runtime;
    private AgeEnum agelimit;
    @Column(length = 10000)
    private String resume;

    @ElementCollection(targetClass = GenreEnum.class)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movieid"))
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> genres;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL) // Add cascade to handle cascade operations
    private List<Showing> showings;

    @ManyToOne
    @JoinColumn(name = "theater_id") // The name of the foreign key column in the movie table
    private Theater theater;

    // Constructors, getters, setters, and other methods...

    public void addShowing(Showing showing) {
        if (showings == null) {
            showings = new ArrayList<>();
        }
        showings.add(showing);
        showing.setMovie(this); // Set the movie reference in the Showing entity
    }

    public void removeShowing(Showing showing) {
        if (showings != null) {
            showings.remove(showing);
            showing.setMovie(null); // Remove the movie reference in the Showing entity
        }
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public AgeEnum getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(AgeEnum agelimit) {
        this.agelimit = agelimit;
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


}
