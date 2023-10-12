package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.model.Booking;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingRESTController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private final MovieRESTController movieRESTController;

    public BookingRESTController(MovieRESTController movieRESTController, BookingRepository bookingRepository) {
        this.movieRESTController = movieRESTController;
        this.bookingRepository = bookingRepository;
    }

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
    @PostMapping("/createbooking")
    public ResponseEntity<Booking> createBookingFromForm(@RequestParam("customerName") String customerName,
                                                         @RequestParam("phoneNo") int phoneNo,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("bookingNumber") int bookingNumber,
                                                         @RequestParam("seatNumber") int seatNumber,
                                                         @RequestParam("aisle") int aisle,
                                                         @RequestParam("movieid") int movieid) {

        Booking newBooking = new Booking();
        newBooking.setCustomerName(customerName);
        newBooking.setPhoneNo(phoneNo);
        newBooking.setEmail(email);
        newBooking.setBookingNumber(bookingNumber);
        newBooking.setSeatNumber(seatNumber);
        newBooking.setAisle(aisle);
        newBooking.setMovieid(movieid); // Set the movieid

        Booking savedBooking = bookingRepository.save(newBooking);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

}