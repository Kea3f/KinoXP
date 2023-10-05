package com.example.kinoxp.Backend.model;

import com.example.kinoxp.Backend.enums.AgeEnum;
import com.example.kinoxp.Backend.enums.GenreEnum;

import javax.persistence.*;
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
    private String resume;


    @ElementCollection(targetClass = GenreEnum.class)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movieid"))
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> genres;

    @OneToMany(mappedBy = "movie")
    private List<Showing> showing;
}
