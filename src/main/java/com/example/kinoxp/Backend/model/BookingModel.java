package com.example.kinoxp.Backend.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique = true)
    private String booking_number; // An auto-generated booking reference

    private String customer_name;
    private String customer_phone;
    private String customer_email;
    private double total_price;

    @ManyToOne
    @JoinColumn(name = "employee_id") // This should match the column name in your database table
    private EmployeeModel employee;


    @ManyToOne
    @JoinColumn(name = "movie_id") // This should match the column name in your database table
    private MovieModel movie;

    @OneToMany(mappedBy = "booking")
    private List<SeatModel> seats;




}
