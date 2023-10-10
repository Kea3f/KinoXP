package com.example.kinoxp.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controller")
public class MovieController {

    @GetMapping("/calendar")
    public String getCalendar() {
        return "calendar";
    }


}
