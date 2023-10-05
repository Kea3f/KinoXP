package com.example.kinoxp.Backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Showing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showing_id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "movie",referencedColumnName = "movieid")
    private Movie movie;


}
