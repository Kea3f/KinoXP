package com.example.kinoxp.Backend.controller;

import com.example.kinoxp.Backend.model.EmployeeModel;
import com.example.kinoxp.Backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam int employee_number, @RequestParam String password, HttpSession session) {
        EmployeeModel employee = employeeRepository.findByEmployeeNumber(employee_number);

        if (employee != null && employee.getPassword().equals(password)) {
            session.setAttribute("employee", employee);
            return "redirect:/homepage";
        } else {
            return "login"; // husk af lave fejl besked i html siden
        }
    }

    @GetMapping("/homepage")
    public String dashboard(HttpSession session) {
        EmployeeModel employee = (EmployeeModel) session.getAttribute("employee");
        if (employee != null) {
            return "homepage";
        } else {
            return "redirect:/login";
        }
    }




}
