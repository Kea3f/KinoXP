package com.example.kinoxp.Backend.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class BookingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (unique = true)
    private String booking_number; // An auto-generated booking reference

    public String getBooking_number() {
        return booking_number;
    }

    public void setBooking_number(String booking_number) {
        this.booking_number = booking_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public List<SeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatModel> seats) {
        this.seats = seats;
    }

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
