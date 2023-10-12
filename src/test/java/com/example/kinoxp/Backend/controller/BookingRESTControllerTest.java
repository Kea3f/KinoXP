package com.example.kinoxp.Backend.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


import com.example.kinoxp.Backend.model.Booking;
import com.example.kinoxp.Backend.repositories.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BookingRESTControllerTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingRESTController bookingRESTController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingRESTController).build();
    }

    @Test
    public void testCreateBookingFromForm() throws Exception {
        // Arrange
        String customerName = "John Doe";
        int phoneNo = 1234567890;
        String email = "john.doe@example.com";
        int bookingNumber = 1;
        int seatNumber = 10;
        int aisle = 5;

        Booking newBooking = new Booking();
        newBooking.setCustomerName(customerName);
        newBooking.setPhoneNo(phoneNo);
        newBooking.setEmail(email);
        newBooking.setBookingNumber(bookingNumber);
        newBooking.setSeatNumber(seatNumber);
        newBooking.setAisle(aisle);

        when(bookingRepository.save(any(Booking.class))).thenReturn(newBooking);

        // Act
        ResponseEntity<Booking> response = bookingRESTController.createBookingFromForm(customerName, phoneNo, email, bookingNumber, seatNumber, aisle);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newBooking, response.getBody());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
}
