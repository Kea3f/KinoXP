package com.example.kinoxp.Backend.model;

import javax.persistence.*;

@Entity
public class SeatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAisleSeat() {
        return isAisleSeat;
    }

    public void setAisleSeat(boolean aisleSeat) {
        isAisleSeat = aisleSeat;
    }

    private int seatNumber;
    private boolean isAisleSeat;

    @ManyToOne
    @JoinColumn(name = "booking_id") // This should match the column name in your database table
    private BookingModel booking;



}
