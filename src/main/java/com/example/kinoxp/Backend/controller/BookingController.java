package com.example.kinoxp.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @GetMapping("/findByPhoneNo")
    public String findByPhoneNo(){
        return "BookingInformationLookup";
    }

    @GetMapping("/createbooking")
    public String createBooking (){
        return "createBooking";
    }

}



