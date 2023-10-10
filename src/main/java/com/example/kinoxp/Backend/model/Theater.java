package com.example.kinoxp.Backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterid;
    private String theaterName;
    private int seatingCapacity;

   @OneToMany(mappedBy = "theater")
   private List<Showing> showings;

    public int getTheaterid() {
        return theaterid;
    }

    public void setTheaterid(int theaterid) {
        this.theaterid = theaterid;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
