package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.model.Booking;
import com.example.kinoxp.Backend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingRESTController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/findByPhoneNo")
    public ResponseEntity<Booking> findBookingByPhoneNo(@RequestParam("phoneNo") int phoneNo) {
        Booking booking = (Booking) bookingRepository.findByPhoneNo(phoneNo);
        if (booking != null) {
            return new ResponseEntity<Booking>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}