package com.example.kinoxp.Backend.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

@ComponentScan(basePackages = "com.example.kinoxp.Backend.controller")

@Controller
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeController {


    @GetMapping("/login")
    public String loginDisplay() {
        return "login";
    }

    @GetMapping("/homepage")
    public String homepageDisplay(){
        return "homepage";
    }


    @GetMapping("/employeeInfo/{employeeId}")
    public String employeeinfoDisplay(){
        return "employeeInfo";
    }

    @GetMapping("/employeeList")
    public String employeeListDisplay(){
        return "employeeList";
    }


    @GetMapping("/deleteEmployee")
    public String deleteEmployee(){
        return "deleteEmployee";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(){
        return "createEmployee";
    }

    @GetMapping("/editEmployee")
    public String editEmployee() {
        return "editEmployee";
    }

    @GetMapping("/navbar")
    public String navbar(){
        return "navbar";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the login page or another appropriate page
        return "redirect:/login";
    }












}
