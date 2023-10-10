package com.example.kinoxp.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class movieController {

    @GetMapping("/movie")
    public String getMovie(){
        return "calendar";
    }
}
