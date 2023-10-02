package com.example.kinoxp.Backend.model;

import jakarta.persistence.*;

@Entity
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
/*
    @ManyToOne
    private Employee employee; // The employee who made the booking


    @ManyToOne
    private Movie movie; // The movie being booked


 */
    private String customer_name;
    private String customer_phone;
    private String customer_email;
    private double total_price;
    private String booking_number; // An auto-generated booking reference

    // @ManyToOne
    //private MovieTheater movieTheater; // The specific movie theater where the booking is made

    // @OneToMany(mappedBy = "booking")
    //private List<Seat> seats; // Flere sæder er muligt af booke - skal der være et maks? nok ik

    // tilføje getters, and setters


}
