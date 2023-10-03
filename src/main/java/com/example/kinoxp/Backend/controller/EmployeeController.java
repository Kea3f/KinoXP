package com.example.kinoxp.Backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
//Login method for employees





}
