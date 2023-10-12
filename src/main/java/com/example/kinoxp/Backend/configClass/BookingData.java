package com.example.kinoxp.Backend.configClass;

import com.example.kinoxp.Backend.model.Booking;
import com.example.kinoxp.Backend.model.Movie;
import com.example.kinoxp.Backend.repositories.BookingRepository;
import com.example.kinoxp.Backend.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class BookingData implements CommandLineRunner {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {

                    Booking booking1 = new Booking();
                    booking1.setId(1);
                    booking1.setCustomerName("Harry");
                    booking1.setPhoneNo(61685162);
                    booking1.setEmail("Harrytrendstyles@gmail.com");
                    booking1.setBookingNumber(29);
                    booking1.setSeatNumber(12);
                    booking1.setAisle(7);
                    booking1.setMovie(movieRepository.getById(1));
                    bookingRepository.save(booking1);


            Booking booking2 = new Booking();
            booking2.setId(2);
            booking2.setCustomerName("Alicia");
            booking2.setPhoneNo(52351234);
            booking2.setEmail("alicia@example.com");
            booking2.setBookingNumber(20);
            booking2.setSeatNumber(11);
            booking2.setAisle(31);
            booking2.setMovie(movieRepository.getById(2));
            bookingRepository.save(booking2);

            Booking booking3 = new Booking();
            booking3.setId(3);
            booking3.setCustomerName("Bobby");
            booking3.setPhoneNo(22555678);
            booking3.setEmail("bob@example.com");
            booking3.setBookingNumber(33);
            booking3.setSeatNumber(19);
            booking3.setAisle(22);

            bookingRepository.save(booking3);

            Booking booking4 = new Booking();
            booking4.setId(4);
            booking4.setCustomerName("Eve");
            booking4.setPhoneNo(55229876);
            booking4.setEmail("eve@example.com");
            booking4.setBookingNumber(44);
            booking4.setSeatNumber(8);
            booking4.setAisle(2);
            bookingRepository.save(booking4);

            Booking booking5 = new Booking();
            booking5.setId(5);
            booking5.setCustomerName("Charlie");
            booking5.setPhoneNo(25554321);
            booking5.setEmail("charlie@example.com");
            booking5.setBookingNumber(32);
            booking5.setSeatNumber(1);
            booking5.setAisle(1);
            bookingRepository.save(booking5);

            Booking booking6 = new Booking();
            booking6.setId(6);
            booking6.setCustomerName("Grace");
            booking6.setPhoneNo(20887152);
            booking6.setEmail("grace@example.com");
            booking6.setBookingNumber(36);
            booking6.setSeatNumber(2);
            booking6.setAisle(12);
            bookingRepository.save(booking6);

        }
    }



