package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.model.Booking;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingRESTController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/findByPhoneNo")
    public ResponseEntity<Map<String, Object>> findBookingByPhoneNo(@RequestParam("phoneNo") int phoneNo) {
        Booking booking = bookingRepository.findByPhoneNo(phoneNo);

        if (booking != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", booking.getId());
            response.put("customerName", booking.getCustomerName());
            response.put("email", booking.getEmail());
            response.put("phoneNo", booking.getPhoneNo());
            response.put("aisle", booking.getAisle());
            response.put("seatNumber", booking.getSeatNumber());
            response.put("bookingNumber", booking.getBookingNumber());

            Movie movie = booking.getMovie();
            if (movie != null) {
                response.put("movieTitle", movie.getMovieTitle());
                }
            else {
                response.put("movieTitle", "Movie not found");
            }


            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}